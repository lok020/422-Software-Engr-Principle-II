package net.sf.eclipsecs.sample.checks;

import java.util.List;

import com.google.common.collect.Lists;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LoopCountCheck extends AbstractCheck 
{
	public int totalLoop = 0;
	
	@Override
	public int[] getDefaultTokens() 
	{  return new int[] {  TokenTypes.DO_WHILE, TokenTypes.FOR_ITERATOR, TokenTypes.LITERAL_WHILE}; }
	
	@Override
	public int[] getRequiredTokens() 
	{ return new int[] { TokenTypes.DO_WHILE, TokenTypes.FOR_ITERATOR, TokenTypes.LITERAL_WHILE}; }

	@Override 
	public void visitToken(DetailAST ast)
	{
		final List <DetailAST> mods = Lists.newArrayList();
		DetailAST modifier = ast.getFirstChild();
		
		while (modifier != null)
		{
			if (isLoop(modifier.getType()))
			{
				System.out.println("isLoop");
				totalLoop++;
				System.out.println(totalLoop);
			}
			else
			{ System.out.println("else"); }
			modifier = ast.getNextSibling();
		}
		
	}
	
	
	public int getTotalLoopCount()
	{ return totalLoop; }
	
	public boolean isLoop(int token)
	{
		System.out.println("inside isLoop");
		if (token == TokenTypes.DO_WHILE || token == TokenTypes.FOR_ITERATOR || token == TokenTypes.LITERAL_WHILE)
		{ 
			System.out.println("true");

			return true; 
		}
		System.out.println("false");

		return false;
	}
}

