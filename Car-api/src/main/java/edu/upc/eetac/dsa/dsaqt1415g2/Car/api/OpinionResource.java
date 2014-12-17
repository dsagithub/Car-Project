package edu.upc.eetac.dsa.dsaqt1415g2.Car.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Opinion;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.OpinionCollection;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Posicion;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.PosicionCollection;

@Path("/opinion")
public class OpinionResource {

	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	// GET TODAS LAS OPINIONES DE UN USUARIO POR SU USERNAME
			private String GET_OPINIONES_USERNAME_QUERY = "select * from opiniones where username=? order by idposicion desc";
			@GET
			@Path("/{username}")
			@Produces(MediaType.CAR_API_OPINION_COLLECTION)
			public OpinionCollection getOpiniones(@PathParam("username") String username) 
			{
				OpinionCollection opiniones = new OpinionCollection();

				Connection conn = null;
				try {
					conn = ds.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(GET_OPINIONES_USERNAME_QUERY);
					stmt.setString(1, String.valueOf(username));

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) 
					{
						Opinion posicion = new Opinion();
						posicion.setIdposicion(rs.getInt("idposicion"));
						posicion.setUsername(rs.getString("username"));
						posicion.setIdopinion(rs.getInt("idopinion"));
						posicion.setContent(rs.getString("content"));
						posicion.setPrecio(rs.getString("precio"));
						posicion.setFecha(rs.getTimestamp("fecha").getTime());
						opiniones.addOpiniones(posicion);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (stmt != null)
							stmt.close();
						conn.close();
					} catch (SQLException e) {
					}
				}

				return opiniones;
			}
	// GET TODAS LAS OPINIONES DE UN ZONA EN CONCRETA BUSCADO POR COORDENADA X
			private String GET_OPINIONES_POR_ZONA_QUERY = "SELECT o.* FROM opiniones o INNER JOIN posiciones p ON o.idposicion=p.idposicion WHERE p.coordenadaX LIKE ?";
			@GET
			@Path("/zona")
			@Produces(MediaType.CAR_API_OPINION_COLLECTION)
			public OpinionCollection getOpinionesCerca(@QueryParam("coordenadax") String coordenadax)
			{
				OpinionCollection opiniones = new OpinionCollection();

				Connection conn = null;
				try {
					conn = ds.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(GET_OPINIONES_POR_ZONA_QUERY);
					stmt.setString(1, '%' + coordenadax + '%');

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) 
					{
						Opinion opinion = new Opinion();
						opinion.setIdposicion(rs.getInt("idposicion"));
						opinion.setUsername(rs.getString("username"));
						opinion.setIdopinion(rs.getInt("idopinion"));
						opinion.setContent(rs.getString("content"));
						opinion.setPrecio(rs.getString("precio"));
						opinion.setFecha(rs.getTimestamp("fecha").getTime());
						opiniones.addOpiniones(opinion);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (stmt != null)
							stmt.close();
						conn.close();
					} catch (SQLException e) {
					}
				}

				return opiniones;
			}
			
	
	
}
