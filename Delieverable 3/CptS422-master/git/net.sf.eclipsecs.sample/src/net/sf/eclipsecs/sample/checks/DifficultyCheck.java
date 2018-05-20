package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class DifficultyCheck extends AbstractCheck {
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public void visitToken(DetailAST ast) {
		double difficulty = getDifficulty(ast);
		System.out.println("Difficulty: " + difficulty);
		log(ast.getLineNo(), "difficulty", difficulty);
	}
	// Get Difficulty by using UniqueOperator, UniqueOperand, and TotalOperand
	public double getDifficulty(DetailAST cur) {
		NumberOfOperandCheck a = new NumberOfOperandCheck();
		NumberOfOperatorCheck b = new NumberOfOperatorCheck();
		int n1, n2, N2;
		n1 = b.getUniqueOperator(cur);
		n2 = a.getUniqueOperand(cur);
		N2 = a.getTotalOperand(cur);
		return ((double)n1 / 2) * ((double)N2 / (double)n2);
	}
}
