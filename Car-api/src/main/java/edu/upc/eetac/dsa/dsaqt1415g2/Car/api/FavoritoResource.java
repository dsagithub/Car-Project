package edu.upc.eetac.dsa.dsaqt1415g2.Car.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Favorito;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.FavoritoCollection;

@Path("/favorito")
public class FavoritoResource {
	
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();

	// GET TODOS LOS FAVORITOS DE UN USUARIO POR SU USERNAME
	private String GET_FAVORITOS_OF_USERNAME_QUERY = "select * from favoritos where username=? order by idfavorito desc";
	@GET
	@Path("/usuario/{username}")
	@Produces(MediaType.CAR_API_FAVORITO_COLLECTION)
	public FavoritoCollection getFavoritos(@PathParam("username") String username) 
	{
		FavoritoCollection favoritos = new FavoritoCollection();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_FAVORITOS_OF_USERNAME_QUERY);
			stmt.setString(1, String.valueOf(username));

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Favorito favorito = new Favorito();
				favorito.setIdfavorito(rs.getInt("idfavorito"));
				favorito.setIdposicion(rs.getInt("idposicion"));
				favorito.setUsername(rs.getString("username"));
				favorito.setDescripcion(rs.getString("descripcion"));
				favorito.setFecha(rs.getTimestamp("fecha").getTime());
				favoritos.addFavoritos(favorito);
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

		return favoritos;
	}

	//CREAR FAVORITO
	private String INSERT_FAVORITO_QUERY = "insert into favoritos (idposicion,username,descripcion) values(?,?,?)";
	@POST
	@Consumes(MediaType.CAR_API_FAVORITO)
	@Produces(MediaType.CAR_API_FAVORITO)
	public Favorito createFavorito(Favorito favorito) {
		Connection conn = null;
		try {
			conn = ds.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_FAVORITO_QUERY,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, favorito.getIdposicion());
			stmt.setString(2, favorito.getUsername());
			stmt.setString(3, favorito.getDescripcion());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				int favoritoid = rs.getInt(1);
				favorito = getFavorito(Integer.toString(favoritoid));
			} else {

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
		return favorito;
	}

	//GET FAVORITO
	private String GET_FAVORITO_BY_ID="select * from favoritos where idfavorito=?";
	@GET
	@Path("/{idfavorito}")
	@Produces(MediaType.CAR_API_FAVORITO)
	public Favorito getFavorito(@PathParam("idfavorito") String idfavorito)
	{
		Favorito favorito = new Favorito();
		Connection conn = null;
		try 
		{
		conn = ds.getConnection();
		} 
		catch (SQLException e) 
		{
		e.printStackTrace();
		}
		 
		PreparedStatement stmt = null; 
		try
		{
			stmt=conn.prepareStatement(GET_FAVORITO_BY_ID);
			stmt.setInt(1, Integer.valueOf(idfavorito));
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				favorito.setIdfavorito(rs.getInt("idfavorito"));
				favorito.setIdposicion(rs.getInt("idposicion"));
				favorito.setUsername(rs.getString("username"));
				favorito.setDescripcion(rs.getString("descripcion"));
				favorito.setFecha(rs.getTimestamp("fecha").getTime());
			}
		}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		finally
		{
			try
			{
				if(stmt !=null)
			    stmt.close();
				
			}catch(SQLException e)
			{
				
			}
		}
		return favorito;
	}
}
