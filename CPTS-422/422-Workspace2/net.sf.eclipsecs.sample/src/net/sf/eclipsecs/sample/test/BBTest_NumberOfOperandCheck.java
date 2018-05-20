//Coding by Sungsu
package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import antlr.Token;
import antlr.TokenStream;

import net.sf.eclipsecs.sample.checks.NumberOfOperandCheck;;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NumberOfOperandCheck.class,DetailAST.class})
public class BBTest_NumberOfOperandCheck {

	public static boolean arraySearch(int[] arr, int value)
	{
		for(int i = 0; i<arr.length; i++)
		{
			if(arr[i] == value)
				return true;
		}
		return false;
	}
	
	public static int[] getAllPossibleTokens()
	{
		ArrayList<Integer> tokens = new ArrayList<Integer>();
		Field[] fields = TokenTypes.class.getDeclaredFields();
		int token = 0;
		for(Field f : fields)
		{
			try
			{
				Class t = f.getType();
				if(t== int.class) 
				{
					token = f.getInt(f);
					tokens.add(token);
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int[] r = new int[fields.length];
		for(int i = 0; i< r.length; i++)
		{
			r[i] = tokens.get(i);
		}
		return r;
	}
	
	@Test
	public void getDefaultTokensTest()
	{
		//Logic Function Testing
		NumberOfOperandCheck myCheck = spy(new NumberOfOperandCheck());
		int[] defaultTokens = myCheck.getDefaultTokens();
		
		assertTrue(defaultTokens.length == 4);	//length check
		assertTrue(arraySearch(defaultTokens, TokenTypes.PLUS) || 
				arraySearch(defaultTokens, TokenTypes.MINUS)||
				arraySearch(defaultTokens, TokenTypes.STAR)||
				arraySearch(defaultTokens, TokenTypes.DIV));
	}

	
	@Test
	public void getRequiredTokens()
	{
		//Logic Function Testing
		NumberOfOperandCheck myCheck = spy(new NumberOfOperandCheck());
		int[] requiredTokens = myCheck.getRequiredTokens();
		
		assertTrue(requiredTokens.length == 4);	//length check
		assertTrue(arraySearch(requiredTokens, TokenTypes.PLUS) || 
				arraySearch(requiredTokens, TokenTypes.MINUS)||
				arraySearch(requiredTokens, TokenTypes.STAR)||
				arraySearch(requiredTokens, TokenTypes.DIV));
	}
	
	
	/*
	 * 
	 * public void visitToken(DetailAST ast)
	 * */
	
	@Test
	public void isOperandTest()
	{
		NumberOfOperandCheck myCheck = spy(new NumberOfOperandCheck());
		assertTrue(myCheck.isOperand(TokenTypes.PLUS));
		assertTrue(myCheck.isOperand(TokenTypes.MINUS));
		assertTrue(myCheck.isOperand(TokenTypes.STAR));
		assertTrue(myCheck.isOperand(TokenTypes.DIV));
		assertFalse(myCheck.isOperand(TokenTypes.ANNOTATION));		
		assertFalse(myCheck.isOperand(TokenTypes.COLON));		
		assertFalse(myCheck.isOperand(TokenTypes.TYPE_PARAMETERS));		
		assertFalse(myCheck.isOperand(TokenTypes.STAR_ASSIGN));
	}
	
	
	@Test
	public void visitTokenTest()
	{
		NumberOfOperandCheck myCheck = spy(new NumberOfOperandCheck());
		int[] allpossibleInputs = getAllPossibleTokens();
		
		//creating input ast
		DetailAST astMock = PowerMockito.mock(DetailAST.class);
		for(int i = 0; i< allpossibleInputs.length; i++)
		{
			DetailAST temp = new DetailAST();
			temp.setType(allpossibleInputs[i]);
			astMock.addChild(temp);
		}
		//The AST has all possible tokens
		myCheck.visitToken(astMock);
		assertTrue(myCheck.count == 4);
	}
	
	
}
