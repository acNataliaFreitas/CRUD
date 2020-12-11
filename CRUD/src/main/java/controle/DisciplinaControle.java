package controle;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import DAO.disciplinaDAO;
import dominio.disciplina;

@Controller
	public class DisciplinaControle {

		
		@GetMapping("/disciplina")
		public String disciplinasTabela(Model modelo) {
			disciplinaDAO dao = new disciplinaDAO();
			List<disciplina> lista = dao.todos();
			modelo.addAttribute("lista",lista);
			return "disciplina"; 
		}
	}
