package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import net.sf.eclipsecs.sample.checks.DistinctOperatorsCheck;
import net.sf.eclipsecs.sample.checks.LoopCountCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DistinctOperatorsCheck.class,DetailAST.class})
public class DistinctOperatorCheckTest {

	@Test
	public void testGetDefaultTokens() {
		final DistinctOperatorsCheck opCountCheckObj = new DistinctOperatorsCheck();
		final int[] actual = opCountCheckObj.getDefaultTokens();
		final int[] expected = {TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
        		TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR,
        		TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, 
        		TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP,
        		TokenTypes.LAND, TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR,
        		TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN,
        		TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC,
        		TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN,
        		TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS};
		 
		assertNotNull("Acceptable tokens should not be null", actual);
        assertArrayEquals("Invalid acceptable tokens", expected, actual);
	}
	
	 @Test
	    public void testGetRequiredTokens() {
	        final DistinctOperatorsCheck myCheck = new DistinctOperatorsCheck();
	        final int[] actual = myCheck.getRequiredTokens();
	        final int[] expected = {TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
	        		TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR,
	        		TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, 
	        		TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP,
	        		TokenTypes.LAND, TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR,
	        		TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN,
	        		TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC,
	        		TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN,
	        		TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS};
	        
	        assertNotNull("Required tokens should not be null", actual);
	        assertArrayEquals("Invalid required tokens", expected, actual);
	    }

	 @Test
		public void visitTokenTest()
		{
			DistinctOperatorsCheck myCheck = spy(new DistinctOperatorsCheck());
			
			DetailAST astMock = PowerMockito.mock(DetailAST.class);
			DetailAST astChildMock = PowerMockito.mock(DetailAST.class);
			
			// loop count should be at 0
			assertEquals(0, myCheck.getTotalDistinctOp());
			
			when(astMock.branchContains(TokenTypes.ASSIGN)).thenReturn(true);
			when(astMock.getFirstChild()).thenReturn(astChildMock);
			when(astMock.findFirstToken(TokenTypes.ASSIGN)).thenReturn(null);
			when(astChildMock.getType()).thenReturn(TokenTypes.ASSIGN);
			myCheck.visitToken(astMock);
			assertEquals(1, myCheck.getTotalDistinctOp());
			
			// should increment loop count
			when(astMock.findFirstToken(TokenTypes.INC)).thenReturn(null);
			myCheck.visitToken(astMock);
			assertEquals(2, myCheck.getTotalDistinctOp());
			
			DistinctOperatorsCheck myCheck2 = spy(new DistinctOperatorsCheck());

			DetailAST astMock2 = PowerMockito.mock(DetailAST.class);
			DetailAST astChildMock2 = PowerMockito.mock(DetailAST.class);
			
			// should not increment
			// should be 0 -- unacceptable token
			when(astMock2.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(true);
			when(astMock2.getFirstChild()).thenReturn(astChildMock2);
			when(astMock2.findFirstToken(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(null);
			when(astChildMock2.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);
			myCheck2.visitToken(astMock2);
			assertEquals(0, myCheck2.getTotalDistinctOp());
			
		}
	 
	 @Test
		public void isDistinctOpTest()
		{
			DistinctOperatorsCheck myCheck = spy(new DistinctOperatorsCheck());

			// checking if correct tokens return true
			assertTrue(myCheck.isDistinctOp(TokenTypes.ASSIGN));
			assertTrue(myCheck.isDistinctOp(TokenTypes.INC));
			assertTrue(myCheck.isDistinctOp(TokenTypes.PLUS));
			
			// checking if non operator tokens return false
			assertFalse(myCheck.isDistinctOp(TokenTypes.BLOCK_COMMENT_BEGIN));
			assertFalse(myCheck.isDistinctOp(TokenTypes.ANNOTATION_ARRAY_INIT));
			assertFalse(myCheck.isDistinctOp(98273498));
		}

	 @Test
		public final void testVisitTokenDetailAST() {
			DistinctOperatorsCheck myCheck = spy(new DistinctOperatorsCheck());
			
			DetailAST astMock = PowerMockito.mock(DetailAST.class);
			DetailAST astChildMock = PowerMockito.mock(DetailAST.class);
			when(astMock.branchContains(TokenTypes.IDENT)).thenReturn(true);
			when(astMock.getFirstChild()).thenReturn(astChildMock);
			when(astMock.getNextSibling()).thenReturn(null);
			when(astChildMock.getType()).thenReturn(TokenTypes.IDENT);
			when(astChildMock.getText()).thenReturn("i");

		}

}
