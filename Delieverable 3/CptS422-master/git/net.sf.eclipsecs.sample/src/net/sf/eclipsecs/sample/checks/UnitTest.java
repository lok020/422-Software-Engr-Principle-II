package net.sf.eclipsecs.sample.checks;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.TreeWalker;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

@PrepareForTest({NumberOfOperandCheck.class, NumberOfOperatorCheck.class,
	LengthOfOOCheck.class,VolumeOfOOCheck.class,DifficultyCheck.class,VocabularyCheck.class})
@RunWith(PowerMockRunner.class)
public class UnitTest extends MethodLimitCheck {	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private DetailAST getAST(String filename) throws Exception {
		File file = new File(System.getProperty("user.dir") + "\\src\\UnitTesting\\" + filename);
		List<String> lines = Files
				.readAllLines(Paths.get(System.getProperty("user.dir") + "\\src\\UnitTesting\\" + filename));
		FileText text = FileText.fromLines(file, lines);
		FileContents contents = new FileContents(text);
		return TreeWalker.parseWithComments(contents);
	}

	@Test
	public void testNumberOfOperandCheck() throws Throwable {
		DetailAST ast = getAST("main.java");
		//spy NumberOfOperandCheck for unit test
		NumberOfOperandCheck spyA = spy(new NumberOfOperandCheck());
		doReturn(3).when(spyA).getTotalOperand(ast);
		doReturn(2).when(spyA).getUniqueOperand(ast);
		//real NumberOfOperandCheck		
		NumberOfOperandCheck realA = new NumberOfOperandCheck();
		assertEquals(realA.getTotalOperand(ast),spyA.getTotalOperand(ast));
		assertEquals(realA.getUniqueOperand(ast),spyA.getUniqueOperand(ast));
		//verify once
		verify(spyA).getTotalOperand(ast);
		verify(spyA).getUniqueOperand(ast);
	}

	@Test
	public void testNumberOfOperatorCheck() throws Throwable {
		DetailAST ast = getAST("main.java");
		//spy NumberOfOperatorCheck for unit test
		NumberOfOperatorCheck spyA = spy(new NumberOfOperatorCheck());
		doReturn(3).when(spyA).getTotalOperator(ast);
		doReturn(2).when(spyA).getUniqueOperator(ast);
		//real NumberOfOperatorCheck	
		NumberOfOperatorCheck realA = new NumberOfOperatorCheck();
		assertEquals(realA.getTotalOperator(ast),spyA.getTotalOperator(ast));
		assertEquals(realA.getUniqueOperator(ast),spyA.getUniqueOperator(ast));
		//verify once
		verify(spyA).getTotalOperator(ast);
		verify(spyA).getUniqueOperator(ast);
	}

	@Test
	public void testLengthOfOOCheck() throws Throwable {	
		DetailAST ast = getAST("main.java");
		//mock NumberOfOperatorCheck by using getTotalOperator
		NumberOfOperatorCheck mockA = PowerMockito.mock(NumberOfOperatorCheck.class);
		PowerMockito.whenNew(NumberOfOperatorCheck.class).withNoArguments().thenReturn(mockA);
		doReturn(1).when(mockA).getTotalOperator(ast);
		//mock NumberOfOperandCheck by using getTotalOperand
		NumberOfOperandCheck mockB = PowerMockito.mock(NumberOfOperandCheck.class);
		PowerMockito.whenNew(NumberOfOperandCheck.class).withNoArguments().thenReturn(mockB);
		doReturn(1).when(mockB).getTotalOperand(ast);
		//real LengthOfOOCheck for test
		LengthOfOOCheck a = new LengthOfOOCheck();
		assertEquals(1+1, a.getLen(ast));
		//verify once
		verify(mockA).getTotalOperator(ast);
		verify(mockB).getTotalOperand(ast);
	}
	
	@Test
	public void testVolumeOfOOCheck() throws Throwable{
		DetailAST ast = getAST("main.java");
		//mock LengthOfOOCheck by using getLen
		LengthOfOOCheck mockA = PowerMockito.mock(LengthOfOOCheck.class);
		PowerMockito.whenNew(LengthOfOOCheck.class).withNoArguments().thenReturn(mockA);
		doReturn(2).when(mockA).getLen(ast);
		//mock VocabularyCheck by using getVoc
		VocabularyCheck mockB = PowerMockito.mock(VocabularyCheck.class);
		PowerMockito.whenNew(VocabularyCheck.class).withNoArguments().thenReturn(mockB);
		doReturn(2).when(mockB).getVoc(ast);
		//real VolumeOfOOCheck for test
		VolumeOfOOCheck a = new VolumeOfOOCheck();
		assertTrue(a.getVolume(ast) == (2)*(Math.log(2)/Math.log(2)));
		//verify once
		verify(mockA).getLen(ast);
		verify(mockB).getVoc(ast);
	}
	
	@Test
	public void testVocabularyCheck() throws Throwable {
		DetailAST ast = getAST("main.java");
		//mock NumberOfOperatorCheck by using getUniqueOperator
		NumberOfOperatorCheck mockA = PowerMockito.mock(NumberOfOperatorCheck.class);
		PowerMockito.whenNew(NumberOfOperatorCheck.class).withNoArguments().thenReturn(mockA);
		doReturn(2).when(mockA).getUniqueOperator(ast);
		//mock NumberOfOperandCheck by using getUniqueOperand
		NumberOfOperandCheck mockB = PowerMockito.mock(NumberOfOperandCheck.class);
		PowerMockito.whenNew(NumberOfOperandCheck.class).withNoArguments().thenReturn(mockB);
		doReturn(1).when(mockB).getUniqueOperand(ast);
		//real VocabularyCheck for test
		VocabularyCheck a = new VocabularyCheck();
		assertEquals(2+1, a.getVoc(ast));
		//verify once
		verify(mockA).getUniqueOperator(ast);
		verify(mockB).getUniqueOperand(ast);	
	}
	
	@Test
	public void testDifficultyCheck() throws Throwable {
		DetailAST ast = getAST("main.java");
		//mock NumberOfOperatorCheck by using getUniqueOperator
		NumberOfOperatorCheck mockA = PowerMockito.mock(NumberOfOperatorCheck.class);
		PowerMockito.whenNew(NumberOfOperatorCheck.class).withNoArguments().thenReturn(mockA);
		doReturn(2).when(mockA).getUniqueOperator(ast);
		//mock NumberOfOperandCheck by using getUniqueOperand and getTotalOperand
		NumberOfOperandCheck mockB = PowerMockito.mock(NumberOfOperandCheck.class);
		PowerMockito.whenNew(NumberOfOperandCheck.class).withNoArguments().thenReturn(mockB);
		doReturn(2).when(mockB).getUniqueOperand(ast);
		doReturn(2).when(mockB).getTotalOperand(ast);
		//real DifficultyCheck for test
		DifficultyCheck a = new DifficultyCheck();
		assertTrue((2/2)*(2/2) == a.getDifficulty(ast));
		//verify once
		verify(mockA).getUniqueOperator(ast);
		verify(mockB).getUniqueOperand(ast);	
		verify(mockB).getTotalOperand(ast);	
	}
}
