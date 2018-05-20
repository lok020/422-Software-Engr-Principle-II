//Coding by Sungsu
package net.sf.eclipsecs.sample.checks;

import java.util.List;

import com.google.common.collect.Lists;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberOfVariableDeclarationCheck extends AbstractCheck 
{
	public int count = 0;
	
	@Override
	public int[] getDefaultTokens() 
	{  return new int[] {  TokenTypes.VARIABLE_DEF}; }
	
	@Override
	public int[] getRequiredTokens() 
	{  return new int[] {  TokenTypes.VARIABLE_DEF}; }

	@Override 
	public void visitToken(DetailAST ast)
	{
		DetailAST modifier = ast.getFirstChild();
		while (modifier != null)
		{
			if (isVarDec(modifier.getType()))
			{
				System.out.println(String.format("{0} is declaration for variable", modifier.getType()));
				count++;
				System.out.println(count);
			}
			else
			{ System.out.println(String.format("{0} is NOT declaration for variable", modifier.getType())); }
			modifier = ast.getNextSibling();
		}
	}
	
	public boolean isVarDec(int token)
	{
		System.out.println("isVarDec?");
		if (token == TokenTypes.VARIABLE_DEF)
		{ 
			System.out.println("true");
			return true; 
		}
		System.out.println("false");
		return false;
	}
}

