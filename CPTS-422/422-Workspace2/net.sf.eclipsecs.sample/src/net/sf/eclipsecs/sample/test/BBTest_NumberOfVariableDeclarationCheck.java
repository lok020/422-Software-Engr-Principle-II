//Coding by Sungsu
package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.LoopCountCheck;
import net.sf.eclipsecs.sample.checks.NumberOfVariableDeclarationCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NumberOfVariableDeclarationCheck.class,DetailAST.class})
public class BBTest_NumberOfVariableDeclarationCheck {

	
	
	@Test
	public void getDefaultTokensTest()
	{
		//Logic Function Testing
		NumberOfVariableDeclarationCheck myCheck = spy(new NumberOfVariableDeclarationCheck());
		int[] defaultTokens = myCheck.getDefaultTokens();
		assertTrue(defaultTokens.length == 1);	//length check
		assertTrue(defaultTokens[0] == TokenTypes.VARIABLE_DEF);
	}
	@Test
	public void getRequiredTokens()
	{
		//Logic Function Testing
		NumberOfVariableDeclarationCheck myCheck = spy(new NumberOfVariableDeclarationCheck());
		int[] requiredTokens = myCheck.getRequiredTokens();
		assertTrue(requiredTokens.length == 1);	//length check
		assertTrue(requiredTokens[0] == TokenTypes.VARIABLE_DEF);
	}
	@Test
	public void isVarDecTest()
	{
		NumberOfVariableDeclarationCheck myCheck = spy(new NumberOfVariableDeclarationCheck());
		assertTrue(myCheck.isVarDec(TokenTypes.VARIABLE_DEF));
		assertFalse(myCheck.isVarDec(TokenTypes.MINUS));
		assertFalse(myCheck.isVarDec(TokenTypes.STAR));
		assertFalse(myCheck.isVarDec(TokenTypes.DIV));
		assertFalse(myCheck.isVarDec(TokenTypes.ANNOTATION));		
		assertFalse(myCheck.isVarDec(TokenTypes.COLON));		
		assertFalse(myCheck.isVarDec(TokenTypes.TYPE_PARAMETERS));		
		assertFalse(myCheck.isVarDec(TokenTypes.STAR_ASSIGN));
	}
	@Test
	public void visitTokenTest()
	{
		NumberOfVariableDeclarationCheck myCheck = spy(new NumberOfVariableDeclarationCheck());

		//creating input ast
		DetailAST astMock = PowerMockito.mock(DetailAST.class);
		
		//AST with 1 variable definition
		int[] myInputs = {};
		for(int i = 0; i< myInputs.length; i++)
		{
			DetailAST temp = new DetailAST();
			temp.setType(myInputs[i]);
			astMock.addChild(temp);
		}
		//The AST has all possible tokens
		myCheck.visitToken(astMock);
		assertTrue(myCheck.count == 0);
		
		//AST with 1 variable definition
		int[] myInputs2 = {TokenTypes.ASSIGN, TokenTypes.BOR, TokenTypes.LITERAL_THROWS, TokenTypes.VARIABLE_DEF};
		for(int i = 0; i< myInputs2.length; i++)
		{
			DetailAST temp = new DetailAST();
			temp.setType(myInputs2[i]);
			astMock.addChild(temp);
		}
		//The AST has all possible tokens
		myCheck.visitToken(astMock);
		assertTrue(myCheck.count == 1);
		
		
		//AST with 2 variable definition 
		int[] myInputs3 = {TokenTypes.VARIABLE_DEF, TokenTypes.VARIABLE_DEF, TokenTypes.EOF};
		for(int i = 0; i< myInputs3.length; i++)
		{
			DetailAST temp = new DetailAST();
			temp.setType(myInputs3[i]);
			astMock.addChild(temp);
		}
		//The AST has all possible tokens
		myCheck.visitToken(astMock);
		assertTrue(myCheck.count == 2);
	}
	
	
	@Test
	public void isLoopTest()
	{
		LoopCountCheck myCheck = spy(new LoopCountCheck());
		
		// checking if correct tokens return true
		assertTrue(myCheck.isLoop(TokenTypes.FOR_ITERATOR));
		assertTrue(myCheck.isLoop(TokenTypes.DO_WHILE));
		assertTrue(myCheck.isLoop(TokenTypes.LITERAL_WHILE));
		
		// checking if non loop tokens return false
		assertFalse(myCheck.isLoop(TokenTypes.ABSTRACT));
		assertFalse(myCheck.isLoop(TokenTypes.BOR));
		assertFalse(myCheck.isLoop(TokenTypes.ENUM_DEF));
		assertFalse(myCheck.isLoop(34843));

	}
	
}
