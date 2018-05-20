package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LengthOfOOCheck extends AbstractCheck {
	
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public void visitToken(DetailAST ast) {
		int length = getLen(ast);
		System.out.println("Length: " + length);
		log(ast.getLineNo(), "lengthOfOO", length);
	}
	// Get length by using TotalOperand and TotalOperator
	public int getLen(DetailAST cur) {
		NumberOfOperandCheck a = new NumberOfOperandCheck();
		NumberOfOperatorCheck b = new NumberOfOperatorCheck();
		return a.getTotalOperand(cur) + b.getTotalOperator(cur);
	}
}