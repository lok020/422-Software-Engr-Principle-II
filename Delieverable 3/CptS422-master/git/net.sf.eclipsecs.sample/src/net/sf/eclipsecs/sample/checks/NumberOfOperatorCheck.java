package net.sf.eclipsecs.sample.checks;

import java.util.HashMap;
import java.util.Map;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberOfOperatorCheck extends AbstractCheck {
	private int totalOperator = 0;
	private int unique_operator = 0;
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public void visitToken(DetailAST ast) {
		totalOperator = this.getTotalOperator(ast);
		System.out.println("operator: " + totalOperator);
		unique_operator = this.getUniqueOperator(ast);
		System.out.println("unique operator: " + unique_operator);
		log(ast.getLineNo(), "numberOfOperator", totalOperator/2);
		log(ast.getLineNo(), "uniqueOfOperator", unique_operator);
	}

	/*
	 * getter for total Operator
	 * */
	public int getTotalOperator(DetailAST cur) {
		totalOperator = 0;
		map = new HashMap<Integer, Integer>();
		recFindOperator(cur);
		return this.totalOperator;
	}

	/*
	 * getter for total Operator
	 * */
	public int getUniqueOperator(DetailAST cur) {
		unique_operator = 0;
		map = new HashMap<Integer, Integer>();
		recFindOperator(cur);
		return this.map.size();
	}

	//using recursion to traverse ever node
	public void recFindOperator(DetailAST cur) {
		if (cur == null) {
			return;
		}
		if (cur.getType() == TokenTypes.ASSIGN || cur.getType() == TokenTypes.BAND
				|| cur.getType() == TokenTypes.BAND_ASSIGN || cur.getType() == TokenTypes.BNOT
				|| cur.getType() == TokenTypes.BOR || cur.getType() == TokenTypes.BOR_ASSIGN
				|| cur.getType() == TokenTypes.BSR || cur.getType() == TokenTypes.BSR_ASSIGN
				|| cur.getType() == TokenTypes.BXOR || cur.getType() == TokenTypes.BXOR_ASSIGN
				|| cur.getType() == TokenTypes.COLON || cur.getType() == TokenTypes.COMMA
				|| cur.getType() == TokenTypes.DEC || cur.getType() == TokenTypes.DIV
				|| cur.getType() == TokenTypes.DIV_ASSIGN || cur.getType() == TokenTypes.DOT
				|| cur.getType() == TokenTypes.EQUAL || cur.getType() == TokenTypes.GE || cur.getType() == TokenTypes.GT
				|| cur.getType() == TokenTypes.INC || cur.getType() == TokenTypes.INDEX_OP
				|| cur.getType() == TokenTypes.LAND || cur.getType() == TokenTypes.LITERAL_INSTANCEOF
				|| cur.getType() == TokenTypes.LNOT || cur.getType() == TokenTypes.LOR || cur.getType() == TokenTypes.LT
				|| cur.getType() == TokenTypes.MINUS || cur.getType() == TokenTypes.MINUS_ASSIGN
				|| cur.getType() == TokenTypes.MOD || cur.getType() == TokenTypes.MOD_ASSIGN
				|| cur.getType() == TokenTypes.NOT_EQUAL || cur.getType() == TokenTypes.PLUS
				|| cur.getType() == TokenTypes.PLUS_ASSIGN || cur.getType() == TokenTypes.POST_DEC
				|| cur.getType() == TokenTypes.POST_INC || cur.getType() == TokenTypes.QUESTION
				|| cur.getType() == TokenTypes.SL || cur.getType() == TokenTypes.SL_ASSIGN
				|| cur.getType() == TokenTypes.SR || cur.getType() == TokenTypes.SR_ASSIGN
				|| cur.getType() == TokenTypes.STAR || cur.getType() == TokenTypes.STAR_ASSIGN
				|| cur.getType() == TokenTypes.UNARY_MINUS || cur.getType() == TokenTypes.UNARY_PLUS) {
			this.totalOperator++;
			Object numOfKey = map.get(cur.getType());
			if (numOfKey == null) {
				map.put(cur.getType(), 1);
			} else {
				map.put(cur.getType(), (int) numOfKey + 1);
			}
		}

		recFindOperator(cur.getFirstChild());
		recFindOperator(cur.getNextSibling());
	}
}
