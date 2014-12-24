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
import javax.ws.rs.QueryParam;

import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Opinion;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.OpinionCollection;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Posicion;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.PosicionCollection;

@Path("/opinion")
public class OpinionResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	//
	// GET TODAS LAS OPINIONES DE UN USUARIO POR SU USERNAME
	private String GET_OPINIONES_USERNAME_QUERY = "select * from opiniones where username=? order by idposicion desc";
	@GET
	@Path("/{username}")
	@Produces(MediaType.CAR_API_OPINION_COLLECTION)
	public OpinionCollection getOpiniones(@PathParam("username") String username) {
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
			while (rs.next()) {
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
	//
	// GET TODAS LAS OPINIONES DE UN ZONA EN CONCRETA BUSCADO POR COORDENADA
	private String GET_OPINIONES_POR_ZONA_QUERY = "SELECT o.* FROM opiniones o INNER JOIN posiciones p ON o.idposicion=p.idposicion WHERE p.coordenadaX LIKE ?";
	@GET
	@Path("/zona")
	@Produces(MediaType.CAR_API_OPINION_COLLECTION)
	public OpinionCollection getOpinionesZona(
			@QueryParam("coordenadax") String coordenadax) {
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
			while (rs.next()) {
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
	//
	// GET TODAS LAS OPINIONES BUSCADO POR LO QUE DICE LA
	// DESCRIPCION
	private String GET_OPINIONES_POR_OPINIONES_QUERY = "SELECT * FROM opiniones WHERE content LIKE ? order by idposicion desc";
	@GET
	@Path("/palabra")
	@Produces(MediaType.CAR_API_OPINION_COLLECTION)
	public OpinionCollection getOpinionesBusqueda(
			@QueryParam("content") String content) {
		OpinionCollection opiniones = new OpinionCollection();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_OPINIONES_POR_OPINIONES_QUERY);
			stmt.setString(1, '%' + content + '%');

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
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
	//
	// GET TODAS LAS OPINIONES BUSCADO POR PRECIO
	private String GET_OPINIONES_POR_PRECIO_QUERY = "SELECT * FROM opiniones WHERE precio LIKE ? order by idposicion desc";
	@GET
	@Path("/precio")
	@Produces(MediaType.CAR_API_OPINION_COLLECTION)
	public OpinionCollection getOpinionesPrecio(
			@QueryParam("precio") String precio) {
		OpinionCollection opiniones = new OpinionCollection();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_OPINIONES_POR_PRECIO_QUERY);
			stmt.setString(1, '%' + precio + '%');

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
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
	//
	//CREAR UNA OPINION
	private String INSERT_OPINION_QUERY="insert into opiniones (username,idposicion,content,precio) values(?,?,?,?)";
	@POST
	@Consumes(MediaType.CAR_API_OPINION)
	@Produces(MediaType.CAR_API_OPINION)
	public Opinion createOpinion(Opinion opinion)
	{
		Connection conn=null;
		try
		{
			conn=ds.getConnection();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		PreparedStatement stmt=null;
		try
		{
			stmt=conn.prepareStatement(INSERT_OPINION_QUERY,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, opinion.getUsername());
			stmt.setInt(2, opinion.getIdposicion());
			stmt.setString(3, opinion.getContent());
			stmt.setString(4, opinion.getPrecio());
			stmt.executeUpdate();
			ResultSet rs =stmt.getGeneratedKeys();
			
			if(rs.next())
			{
				int opinionid=rs.getInt(1);
				opinion=getOpinion(Integer.toString(opinionid));
			}
			else
			{
				
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
				if (stmt != null)
				stmt.close();
				conn.close();
			} 
			catch (SQLException e) 
			{
				
			}
		}
		return opinion;
	}
	//
	//GET UNA OPINION
	private String GET_OPINION_BY_ID="select * from opiniones where idopinion=?";
	@GET
	@Path("/{idopinion}")
	@Produces(MediaType.CAR_API_OPINION)
	public Opinion getOpinion(@PathParam("idopinion") String idopinion)
	{
		Opinion opinion =new Opinion();
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
			stmt=conn.prepareStatement(GET_OPINION_BY_ID);
			stmt.setInt(1, Integer.valueOf(idopinion));
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				opinion.setIdopinion(rs.getInt("idopinion"));
				opinion.setIdposicion(rs.getInt("idposicion"));
				opinion.setUsername(rs.getString("username"));
				opinion.setContent(rs.getString("content"));
				opinion.setPrecio(rs.getString("precio"));
				opinion.setFecha(rs.getTimestamp("fecha").getTime());
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
		
		return opinion;
	}

}
