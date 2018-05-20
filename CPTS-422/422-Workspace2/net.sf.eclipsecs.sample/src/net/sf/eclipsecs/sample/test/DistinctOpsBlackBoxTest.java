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

import net.sf.eclipsecs.sample.checks.DistinctOperatorsCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DistinctOperatorsCheck.class,DetailAST.class})
public class DistinctOpsBlackBoxTest {

	@Test
	public void DecisionTableTest()
	{
		DistinctOperatorsCheck myCheck = spy(new DistinctOperatorsCheck());
		
		// equals 0
		assertEquals(0, myCheck.getTotalDistinctOp());
		
		DetailAST astMock = PowerMockito.mock(DetailAST.class);
		DetailAST astChildMock = PowerMockito.mock(DetailAST.class);
		
		// C1 -> A1
		when(astMock.branchContains(TokenTypes.ASSIGN)).thenReturn(true);
		when(astMock.getFirstChild()).thenReturn(astChildMock);
		when(astMock.findFirstToken(TokenTypes.ASSIGN)).thenReturn(null);
		when(astChildMock.getType()).thenReturn(TokenTypes.ASSIGN);
		myCheck.visitToken(astMock);
		assertEquals(1, myCheck.getTotalDistinctOp());
		
		// C2 -> A2
		when(astMock.findFirstToken(TokenTypes.PLUS)).thenReturn(null);
		myCheck.visitToken(astMock);
		assertEquals(2, myCheck.getTotalDistinctOp());
		
		// C3 -> A3
		when(astMock.findFirstToken(TokenTypes.MINUS)).thenReturn(null);
		myCheck.visitToken(astMock);
		assertEquals(3, myCheck.getTotalDistinctOp());
		
		// C4 -> A5
		when(astMock.findFirstToken(TokenTypes.ASSIGN)).thenReturn(null);
		myCheck.visitToken(astMock);
		assertEquals(3, myCheck.getTotalDistinctOp());
		
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
	
}
