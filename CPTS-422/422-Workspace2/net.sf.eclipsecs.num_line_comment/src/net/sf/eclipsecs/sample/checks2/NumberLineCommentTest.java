package net.sf.eclipsecs.sample.checks2;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
//import class NumberCount;

import javax.xml.soap.Detail;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.NumberComment;

import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest({NumberLineComment.class, NumberLineCommentTest.class})
public class NumberLineCommentTest {
	@Test
	public void testNumCom() {

		NumberLineComment mockNumCom = mock(NumberLineComment.class);
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
		
		//int check = mockNumCom.Counts(mockAST);
		
		//doReturn("The check is incorrect!").when(mockNumCom).Counts(null);
		//doReturn("The check is incorrect!").when(mockNumCom).Counts(mockAST);
	}
}
