package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import database.DBConnect;

/**
 * Servlet implementation class Login
 */
@WebServlet({"/login", "/Login"})
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//récupère les infos du formulaire ($_POST en php)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//si aucun des deux n'est vide
		if(username != null && password != null){
			//par précaution, j'instancie un User vide
			User user = null;

			try {
				//connexion à la BDD
				Connection con = DBConnect.connect();
				//je créé une requète préparée (avec paramètres '?') en attente d'exécution
				PreparedStatement stmt = con.prepareStatement(
						"SELECT username, email "
						+ "FROM user "
						+ "WHERE username = ? "
						+ "AND password = ?"
				);
				//j'affecte à la place des '?' les bonnes valeurs
				stmt.setString(1, username);
				stmt.setString(2, password);
				
				//j'exécute la requète et récupère les résultats dans un ResultSet
				ResultSet rs = stmt.executeQuery();
				
				//je déplace le pointeur interne du ResultSet sur le premier resultat
				
					
				if(rs.next()) {
					//je créé un utilisateur User avec les infos venant de la BDD
					user = new User(rs.getString("username"), rs.getString("email"));
				}
				
				
				//je ferme la connexion à la BDD (a pu besoin)
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//si l'utilisateur n'est plus null
			if(user != null) {
				//on le met en session
				request.getSession().setAttribute("user", user);
				//on redirige vers l'accueil
				response.sendRedirect("home");
			}	
			else {
				//sinon, msg d'erreur puis renvoi vers le formulaire
				request.setAttribute("msg", "Utilisateur inconnu dans la base de données !");
				this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			}
			
		}
		else {
			request.setAttribute("msg", "Nom d'utilisateur ou mot de passe incorrects !");
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
		
		
		
	}

}
