package model.dao;

public class DaoFactory {
	
	public static ContaDao createContaDAO() {
		return new ContaDaoJDBC();
	}
	
	public static JogoDao createJogoDAO() {
		return new JogoDaoJDBC();
	}
	
	public static ReviewDao createReviewDAO () {
		return new ReviewDaoJDBC();
	}
}
