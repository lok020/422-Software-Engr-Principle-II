package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
//import class NumberCount;

import javax.xml.soap.Detail;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.sf.eclipsecs.sample.checks.NumberComment;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NumberComment.class, NumberCommentTest.class})
public class NumberCommentTest {
	@Test
	public void testNumCom()
	{
		NumberComment mockNumCom = mock(NumberComment.class);
		DetailAST mockAST = PowerMock.createMock(DetailAST.class);
		
		//PowerMock.mockStaticPartial(NumberComment.class, "Counts");
		//EasyMock.expect(NumberComment.Counts(mockAST));
		
		assertNull(mockNumCom.findChildAST(null, 0));
		assertNotNull(mockNumCom.findChildAST(mockAST, TokenTypes.COMMENT_CONTENT));
		assertNotNull(mockNumCom.findChildAST(mockAST, TokenTypes.BLOCK_COMMENT_BEGIN));
		assertNotNull(mockNumCom.findChildAST(mockAST, TokenTypes.BLOCK_COMMENT_END));
		assertNotNull(mockNumCom.findChildAST(mockAST, TokenTypes.SINGLE_LINE_COMMENT));
		
		assertEquals(0,mockNumCom.Counts(null));
		assertNotNull(mockNumCom.Counts(mockAST));	
		
		//System.out.println("checking" );
	}
}
