//Coding by Sungsu
package net.sf.eclipsecs.sample.checks;

import java.util.List;

import com.google.common.collect.Lists;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberOfOperandCheck extends AbstractCheck 
{
	public int count = 0;
	
	@Override
	public int[] getDefaultTokens() 
	{  return new int[] {  TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR}; }
	
	@Override
	public int[] getRequiredTokens() 
	{ return new int[] {  TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR}; }

	@Override 
	public void visitToken(DetailAST ast)
	{
		DetailAST modifier = ast.getFirstChild();
		while (modifier != null)
		{
			if (isOperand(modifier.getType()))
			{
				System.out.println(String.format("{0} is operand", modifier.getType()));
				count++;
				System.out.println(count);
			}
			else
			{ System.out.println(String.format("{0} is NOT operand", modifier.getType())); }
			modifier = ast.getNextSibling();
		}
		
	}
	
	public boolean isOperand(int token)
	{
		System.out.println("inside isOperand?");
		if (token == TokenTypes.PLUS || token == TokenTypes.MINUS || token == TokenTypes.DIV || token == TokenTypes.STAR)
		{ 
			System.out.println("true");
			return true; 
		}
		System.out.println("false");
		return false;
	}
}

