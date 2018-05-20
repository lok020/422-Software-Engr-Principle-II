package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Test;
import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtils;

import net.sf.eclipsecs.sample.checks.DistinctOperatorsCheck;
import net.sf.eclipsecs.sample.checks.LoopCountCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoopCountCheck.class,DetailAST.class})
public class LoopCountCheckTest {


	/*@Test
	public void checkDefaultTokens() {
		final LoopCountCheck myCheck = new LoopCountCheck();
		int[] res = myCheck.getDefaultTokens();
		
		assertEquals(175, res[0]);
		assertEquals(37, res[1]);	
		assertEquals(84, res[2]);
	}*/
	@Test
	public final void testGetDefaultTokens() 
	{
		final LoopCountCheck myCheck = new LoopCountCheck();
        final int[] actual = myCheck.getDefaultTokens();
		System.out.println("actual");
		System.out.println(Arrays.toString(actual));

        final int[] expected = {TokenTypes.DO_WHILE, TokenTypes.FOR_ITERATOR, TokenTypes.LITERAL_WHILE};
       
        
        assertNotNull("Acceptable tokens should not be null", actual);
        assertArrayEquals("Invalid acceptable tokens", expected, actual);
		
	}

	 @Test
	    public void testGetRequiredTokens() {
	        final LoopCountCheck myCheck = new LoopCountCheck();
	        final int[] actual = myCheck.getRequiredTokens();
	        
	        final int[] expected = {TokenTypes.DO_WHILE, TokenTypes.FOR_ITERATOR, TokenTypes.LITERAL_WHILE};
	        
	        assertNotNull("Required tokens should not be null", actual);
	        assertArrayEquals("Invalid required tokens", expected, actual);
	    }
	
	@Test
	public void visitTokenTest()
	{
		LoopCountCheck myCheck = spy(new LoopCountCheck());
		
		DetailAST astMock = PowerMockito.mock(DetailAST.class);
		DetailAST astChildMock = PowerMockito.mock(DetailAST.class);
		
		// loop count should be at 0
		assertEquals(0, myCheck.getTotalLoopCount());
		
		when(astMock.branchContains(TokenTypes.FOR_ITERATOR)).thenReturn(true);
		when(astMock.getFirstChild()).thenReturn(astChildMock);
		when(astMock.findFirstToken(TokenTypes.FOR_ITERATOR)).thenReturn(null);
		when(astChildMock.getType()).thenReturn(TokenTypes.FOR_ITERATOR);
		myCheck.visitToken(astMock);
		assertEquals(1, myCheck.getTotalLoopCount());
		
		// should increment loop count
		when(astMock.findFirstToken(TokenTypes.DO_WHILE)).thenReturn(null);
		myCheck.visitToken(astMock);
		assertEquals(2, myCheck.getTotalLoopCount());
		
		LoopCountCheck myCheck2 = spy(new LoopCountCheck());

		DetailAST astMock2 = PowerMockito.mock(DetailAST.class);
		DetailAST astChildMock2 = PowerMockito.mock(DetailAST.class);
		
		// should not increment
		// should be 0 -- unacceptable token
		when(astMock2.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(true);
		when(astMock2.getFirstChild()).thenReturn(astChildMock2);
		when(astMock2.findFirstToken(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(null);
		when(astChildMock2.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);
		myCheck2.visitToken(astMock2);
		assertEquals(0, myCheck2.getTotalLoopCount());
		
		/*when(astMock.findFirstToken(TokenTypes.DO_WHILE)).thenReturn(null);
		myCheck.visitToken(astMock);
		assertEquals(3, myCheck.getTotalLoopCount());
		
		when(astMock.findFirstToken(TokenTypes.LITERAL_WHILE)).thenReturn(null);
		myCheck.visitToken(astMock);
		assertEquals(4, myCheck.getTotalLoopCount());
		
		when(astMock.findFirstToken(TokenTypes.ABSTRACT)).thenReturn(null);
		myCheck.visitToken(astMock);
		assertEquals(4, myCheck.getTotalLoopCount());*/
	
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
	}
	
	@Test
	public void testGetTotalLoopCount()
	{
		
	}
	@Test
	public final void testVisitTokenDetailAST() {
		LoopCountCheck myCheck = spy(new LoopCountCheck());
		
		DetailAST astMock = PowerMockito.mock(DetailAST.class);
		DetailAST astChildMock = PowerMockito.mock(DetailAST.class);
		when(astMock.branchContains(TokenTypes.IDENT)).thenReturn(true);
		when(astMock.getFirstChild()).thenReturn(astChildMock);
		when(astMock.getNextSibling()).thenReturn(null);
		when(astChildMock.getType()).thenReturn(TokenTypes.IDENT);
		when(astChildMock.getText()).thenReturn("i");

	}

	
}