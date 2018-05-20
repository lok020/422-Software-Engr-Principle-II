package net.sf.eclipsecs.sample.checks;

import java.util.HashMap;
import java.util.Map;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;


public class NumberOfOperandCheck extends AbstractCheck {
	private int totalOperand = 0;
	private int unique_operand = 0;
	private Map<String, Integer> map = new HashMap<String, Integer>();

	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public void visitToken(DetailAST ast) {
		totalOperand = this.getTotalOperand(ast);
		System.out.println("operand: " + totalOperand);

		unique_operand = this.getUniqueOperand(ast);
		System.out.println("unique operand: " + unique_operand);

		log(ast.getLineNo(), "numberOfOperand", totalOperand/2);
		log(ast.getLineNo(), "uniqueOfOperand", unique_operand);
	}
	
	/*
	 * getter for total operand
	 * */
	public int getTotalOperand(DetailAST cur)
	{
		totalOperand = 0;
		map = new HashMap<String, Integer>();
		recFindOperand(cur);
		return this.totalOperand;
	}
	
	/*
	 * getter for unique operand
	 * */
	public int getUniqueOperand(DetailAST cur)
	{
		unique_operand = 0;
		map = new HashMap<String, Integer>();
		
		recFindOperand(cur);
		return this.map.size();
	}
	
	//using recursion to traverse ever node
	public void recFindOperand(DetailAST cur) {
		if (cur == null) {
			return;
		}
		//cur.getType() == TokenTypes.IDENT
		if (cur.getType() == TokenTypes.NUM_INT	|| cur.getType() == TokenTypes.NUM_FLOAT) {
			this.totalOperand++;

			Object numOfKey = map.get(cur.getText());
			if (numOfKey == null) {
				map.put(cur.getText(), 1);
			} else {
				map.put(cur.getText(), (int) numOfKey + 1);
			}
		}

		recFindOperand(cur.getFirstChild());
		recFindOperand(cur.getNextSibling());
	}
}
