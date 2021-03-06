package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.disciplina;

public class disciplinaDAO {
	
	public void inserir(disciplina disciplina) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "insert into disciplina" + "(nome,professor,periodo,codigo_sala_classroom)"
				+ " values (?,?,?,?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, disciplina.getNome());
			stmt.setString(2, disciplina.getProfessor());
			stmt.setInt(3, disciplina.getPeriodo());
			stmt.setString(4, disciplina.getCodigo());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<disciplina> todos() {
		List<disciplina> lista = new ArrayList<disciplina>();
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from disciplina;";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			ResultSet retorno = stmt.executeQuery();
			while (retorno.next()) {
				int id = retorno.getInt("id");
				String nomeDisciplina = retorno.getString("nome");
				int periodo = retorno.getInt("periodo");
				String professor = retorno.getString("professor");
				String cod = retorno.getString("codigo_sala_classroom");
				disciplina disciplina = new disciplina(id, periodo, nomeDisciplina, professor, cod);
				lista.add(disciplina);
			}
			retorno.close();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	public void excluir(int id) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "delete from disciplina where id = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(disciplina disciplina) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "update disciplina set nome=?,professor=?,periodo=?,codigo_sala_classroom=?"
				+ " where id = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, disciplina.getNome());
			stmt.setString(2, disciplina.getProfessor());
			stmt.setInt(3, disciplina.getPeriodo());
			stmt.setString(4, disciplina.getCodigo());
			stmt.setInt(5, disciplina.getId());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public disciplina buscaPorId(int id) {
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from disciplina where id = ?;";
		disciplina disciplina = null;
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet retorno = stmt.executeQuery();
			retorno.next();
			String nomeDisciplina = retorno.getString("nome");
			int periodo = retorno.getInt("periodo");
			String professor = retorno.getString("professor");
			String cod = retorno.getString("codigo_sala_classroom");
			disciplina = new disciplina(id, periodo, nomeDisciplina, professor, cod);
			stmt.close();
			conexao.close();
		} catch (

		SQLException e) {
			System.out.println(e.getMessage());
		}
		return disciplina;
	}
	}

