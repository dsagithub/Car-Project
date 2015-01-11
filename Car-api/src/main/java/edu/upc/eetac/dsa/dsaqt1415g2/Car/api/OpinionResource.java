package edu.upc.eetac.dsa.dsaqt1415g2.Car.api;

import java.sql.*;

import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Favorito;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Opinion;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.OpinionCollection;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Posicion;


@Path("/opinion")
public class OpinionResource 
{
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	@Context
	private SecurityContext security;

	// GET TODAS LAS OPINIONES DE UN USUARIO POR SU USERNAME
	private String GET_OPINIONES_USERNAME_QUERY = "select * from opiniones where username=? order by idposicion desc";
	
	
	@GET
	@Produces(MediaType.CAR_API_OPINION_COLLECTION)
	public OpinionCollection getOpiniones(@QueryParam("username") String username) 
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
			stmt.setString(1, username);
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
			
			opiniones.setUsername(username);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) 
			{
				
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
	public OpinionCollection getOpinionesZona(@QueryParam("coordenadaX") String coordenadaX) 
	{
		OpinionCollection opiniones = new OpinionCollection();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_OPINIONES_POR_ZONA_QUERY);
			stmt.setString(1, '%' + coordenadaX + '%');

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
	// GET TODAS LAS OPINIONES BUSCADO POR LO QUE DICE LA content
	private String GET_OPINIONES_POR_OPINIONES_QUERY = "SELECT * FROM opiniones WHERE content LIKE ? order by idposicion desc";
	@GET
	@Path("/palabra")
	@Produces(MediaType.CAR_API_OPINION_COLLECTION)
	public OpinionCollection getOpinionesBusqueda(@QueryParam("content") String content) 
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
		} 
		finally 
		{
			try 
			{
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) 
			{
				
			}
		}

		return opiniones;
	}
	
	//
	// GET TODAS LAS OPINIONES BUSCADO POR PRECIO y una zona 
	//SELECT o.* FROM opiniones o INNER JOIN posiciones p ON o.idposicion=p.idposicion WHERE p.coordenadaX LIKE ?
	private String GET_OPINIONES_POR_PRECIO_ZONA_QUERY = "SELECT o.* FROM opiniones o INNER JOIN posiciones p ON o.idposicion=p.idposicion WHERE p.coordenadaX LIKE ? AND o.precio LIKE ?";
	@GET
	@Path("/precio")
	@Produces(MediaType.CAR_API_OPINION_COLLECTION)
	public OpinionCollection getOpinionesPrecio(@QueryParam("coordenadaX") String coordenadaX,@QueryParam("precio") String precio) 
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
			stmt = conn.prepareStatement(GET_OPINIONES_POR_PRECIO_ZONA_QUERY);
			stmt.setString(1, '%' + coordenadaX + '%');
			stmt.setString(2, '%' + precio + '%');

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
		validateOpinion(opinion);
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
			stmt.setString(1, security.getUserPrincipal().getName());
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
	//
	//DELETE OPINION
	private String DELETE_OPINION_QUERY="delete from opiniones where idopinion=?";
	@DELETE
	@Path("/{idopinion}")
	public void deleteOpinion(@PathParam("idopinion") String idopinion)
	{
		validateUser(idopinion);
		Connection conn=null;
		try
		{
			conn=ds.getConnection();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		PreparedStatement stmt=null;
		try
		{
			stmt=conn.prepareStatement(DELETE_OPINION_QUERY);
			stmt.setInt(1,Integer.valueOf(idopinion));
			int rows = stmt.executeUpdate();
			if(rows==0)
			{
				//deleteting inexisting posicion
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
				if(stmt!=null)
				stmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				
			}
		}		
	}
	//
	//UPDATE OPINION
	private String UPDATE_OPINION_QUERY="update opiniones set content=ifnull(?,content), precio=ifnull(?,precio) where idopinion=?";
	@PUT
	@Path("/{idopinion}")
	@Consumes(MediaType.CAR_API_OPINION)
	@Produces(MediaType.CAR_API_OPINION)
	public Opinion updateOpinion(@PathParam("idopinion") String idopinion, Opinion opinion)
	{
		validateUser(idopinion);
		Connection conn =null;
		try
		{
			conn=ds.getConnection();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		PreparedStatement stmt=null;
		try
		{
			stmt=conn.prepareStatement(UPDATE_OPINION_QUERY);
			
			stmt.setString(1, opinion.getContent());
			stmt.setString(2, opinion.getPrecio());
			stmt.setInt(3, Integer.valueOf(idopinion));
			int rows=stmt.executeUpdate();
			if(rows==1)
				opinion = getOpinion(idopinion);
			else
			{
				//Updateing inexisting posicion description
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
				conn.close();
			}
			catch(SQLException e)
			{
				
			}
		}
		
		return opinion;
		}
	
	//PARA VALIDAR OPINIONES
	private void validateOpinion(Opinion opinion) {
		if (opinion.getContent() == null)
		throw new BadRequestException("La descripcion no puede ser nula");
		if (opinion.getPrecio() == null)
			throw new BadRequestException("La descripcion no puede ser nula");
		}
	//PARA VALIDAR UN USUARIO
	private void validateUser(String idopinion)
	{
		Opinion posicion= getOpinionFromDatabase(idopinion);
		String username=posicion.getUsername();
		if (!security.getUserPrincipal().getName().equals(username)) 
		{
			throw new ForbiddenException("You are not allowed to modify this Description.");
		}
	}
	
	private Opinion getOpinionFromDatabase(String idopinion)
	{
		Opinion opinion=new Opinion();
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
				opinion.setIdposicion(rs.getInt("idposicion"));
				opinion.setUsername(rs.getString("username"));
				opinion.setIdopinion(rs.getInt("idopinion"));
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
