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

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoopCountCheck.class,DetailAST.class})
public class LoopCountBlackBoxTest {

	@Test
	public void DecisionTableTest()
	{
		// C1. Is a Do While
		// C2. Is a For Loop
		// C3. Is a While Loop
		// C4. Not any loop indicator
		
		// A1. Increment loop count
		// A2. Increment loop count
		// A3. Increment loop count
		// A4. Do nothing
		
		LoopCountCheck myCheck = spy(new LoopCountCheck());
		
		// equals 0
		assertEquals(0, myCheck.getTotalLoopCount());
		
		DetailAST astMock = PowerMockito.mock(DetailAST.class);
		DetailAST astChildMock = PowerMockito.mock(DetailAST.class);
		
		// C1 -> A1
		when(astMock.branchContains(TokenTypes.FOR_ITERATOR)).thenReturn(true);
		when(astMock.getFirstChild()).thenReturn(astChildMock);
		when(astMock.findFirstToken(TokenTypes.FOR_ITERATOR)).thenReturn(null);
		when(astChildMock.getType()).thenReturn(TokenTypes.FOR_ITERATOR);
		myCheck.visitToken(astMock);
		assertEquals(1, myCheck.getTotalLoopCount());
		
		// C2 -> A2
		when(astMock.findFirstToken(TokenTypes.DO_WHILE)).thenReturn(null);
		myCheck.visitToken(astMock);
		assertEquals(2, myCheck.getTotalLoopCount());
		
		// C3 -> A3
		when(astMock.findFirstToken(TokenTypes.LITERAL_WHILE)).thenReturn(null);
		myCheck.visitToken(astMock);
		assertEquals(3, myCheck.getTotalLoopCount());
		
		// C4 -> A5
		when(astMock.findFirstToken(TokenTypes.ASSIGN)).thenReturn(null);
		myCheck.visitToken(astMock);
		assertEquals(3, myCheck.getTotalLoopCount());
		
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
