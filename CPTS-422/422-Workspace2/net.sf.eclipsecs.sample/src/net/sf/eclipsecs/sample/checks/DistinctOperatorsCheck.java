package net.sf.eclipsecs.sample.checks;

import java.util.List;

import com.google.common.collect.Lists;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class DistinctOperatorsCheck extends AbstractCheck {

    public int count = 0;
    
    @Override
    public int[] getDefaultTokens() {
        return new int[] {TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
        		TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR,
        		TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, 
        		TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP,
        		TokenTypes.LAND, TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR,
        		TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN,
        		TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC,
        		TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN,
        		TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS};
    }
    
    @Override
	public int[] getRequiredTokens() 
	{ return new int[] {TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
    		TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR,
    		TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, 
    		TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP,
    		TokenTypes.LAND, TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR,
    		TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN,
    		TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC,
    		TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN,
    		TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS};
	}

	@Override 
	public void visitToken(DetailAST ast)
	{
		final List <DetailAST> mods = Lists.newArrayList();
		DetailAST modifier = ast.getFirstChild();
		
		while (modifier != null)
		{
			if (isDistinctOp(modifier.getType()))
			{
				System.out.println("isDistinctOp");
				count++;
				System.out.println(count);
			}
			else
			{ System.out.println("else"); }
			modifier = ast.getNextSibling();
		}
		
	}
	
	public int getTotalDistinctOp()
	{ return count; }
	
	public boolean isDistinctOp(int token)
	{
		System.out.println("inside isDistinctOp");
		if (token == TokenTypes.ASSIGN || token == TokenTypes.BAND|| token == TokenTypes.BAND_ASSIGN || token == TokenTypes.BNOT || 
	    		token == TokenTypes.BOR || token == TokenTypes.BOR_ASSIGN || token == TokenTypes.BSR || token == TokenTypes.BSR_ASSIGN || token == TokenTypes.BXOR ||
	    		token == TokenTypes.BXOR_ASSIGN || token == TokenTypes.COLON || token == TokenTypes.DEC || token == TokenTypes.DIV || token == TokenTypes.DIV_ASSIGN || 
	    		token == TokenTypes.DOT || token == TokenTypes.EQUAL || token == TokenTypes.GE || token == TokenTypes.GT || token == TokenTypes.INC || token == TokenTypes.INDEX_OP ||
	    		token == TokenTypes.LAND || token == TokenTypes.LE || token == TokenTypes.LITERAL_INSTANCEOF || token == TokenTypes.LNOT || token == TokenTypes.LOR ||
	    		token == TokenTypes.LT || token == TokenTypes.MINUS || token == TokenTypes.MINUS_ASSIGN || token == TokenTypes.MOD || token == TokenTypes.MOD_ASSIGN ||
	    		token == TokenTypes.NOT_EQUAL || token == TokenTypes.PLUS || token == TokenTypes.PLUS_ASSIGN || token == TokenTypes.POST_DEC || token == TokenTypes.POST_INC ||
	    		token ==TokenTypes.QUESTION || token == TokenTypes.SL || token == TokenTypes.SL_ASSIGN || token == TokenTypes.SR || token == TokenTypes.SR_ASSIGN ||
	    		token == TokenTypes.STAR || token == TokenTypes.STAR_ASSIGN || token == TokenTypes.UNARY_MINUS || token == TokenTypes.UNARY_PLUS)
		{ 
			System.out.println("true");

			return true; 
		}
		System.out.println("false");

		return false;
	}
}
