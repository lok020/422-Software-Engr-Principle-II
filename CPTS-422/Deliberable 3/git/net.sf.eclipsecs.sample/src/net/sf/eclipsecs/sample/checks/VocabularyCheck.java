package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class VocabularyCheck extends AbstractCheck {
	public int[] getDefaultTokens() {
        return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
    }
	
	 @Override
	    public void visitToken(DetailAST ast) {
	        int vocabulary = getVoc(ast);
	        System.out.println("Vocabulary: " + vocabulary);
	        log(ast.getLineNo(), "vocabulary", vocabulary);
	    }
	 // Get vocabulary by using UniqueOperator and UniqueOperand
	 /*public int getVoc(DetailAST cur)
	   {
		    NumberOfOperatorCheck a = new NumberOfOperatorCheck();
	        NumberOfOperandCheck b = new NumberOfOperandCheck();
	        return a.getUniqueOperator(cur) + b.getUniqueOperand(cur);
	   }*/
	 public int getVoc(DetailAST cur)
	   {
		    NumberOfOperatorCheck a = new NumberOfOperatorCheck();
	        NumberOfOperandCheck b = new NumberOfOperandCheck();
	        return a.getUniqueOperator(cur) - b.getUniqueOperand(cur);
	   }
}
