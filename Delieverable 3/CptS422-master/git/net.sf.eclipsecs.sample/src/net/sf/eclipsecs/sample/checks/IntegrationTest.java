package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.puppycrawl.tools.checkstyle.TreeWalker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class IntegrationTest extends MethodLimitCheck {

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
		// real NumberOfOperandCheck for testing getTotalOperand and getUniqueOperand
		NumberOfOperandCheck a = new NumberOfOperandCheck();
		int TotalOperand = a.getTotalOperand(ast);
		int UniqueOperand = a.getUniqueOperand(ast);
		
		System.out.println("Total Operand: " + TotalOperand);
		System.out.println("Unique Operand: " + UniqueOperand);
		//assert
		assertEquals(3, TotalOperand);
		assertEquals(2, UniqueOperand);
	}

	@Test
	public void testNumberOfOperatorCheck() throws Throwable {
		DetailAST ast = getAST("main.java");
		// real NumberOfOperatorCheck for testing getTotalOperator and getUniqueOperator
		NumberOfOperatorCheck a = new NumberOfOperatorCheck();
		int TotalOperator = a.getTotalOperator(ast);
		int UniqueOperator = a.getUniqueOperator(ast);
		
		System.out.println("Total Operator: " + TotalOperator);
		System.out.println("Unique Operator: " + UniqueOperator);
		//assert
		assertEquals(3, TotalOperator);
		assertEquals(2, UniqueOperator);
	}

	@Test
	public void testLengthOfOOCheck() throws Throwable {
		DetailAST ast = getAST("main.java");
		// real LengthOfOOCheck for testing getLen
		LengthOfOOCheck a = new LengthOfOOCheck();
		int length = a.getLen(ast);
		System.out.println("Length: " + length);
		// spy LengthOfOOCheck for integration test
		LengthOfOOCheck spyA = spy(new LengthOfOOCheck());
		doReturn(6).when(spyA).getLen(ast);
		assertEquals(spyA.getLen(ast), length);	
		//verify once
		verify(spyA).getLen(ast);
	}

	@Test
	public void testVocabularyCheck() throws Throwable {
		DetailAST ast = getAST("main.java");
		// real VocabularyCheck for testing getVoc
		VocabularyCheck a = new VocabularyCheck();
		int vocabulary = a.getVoc(ast);
		System.out.println("Vocabulary: " + vocabulary);
		// spy VocabularyCheck for integration test
		VocabularyCheck spyA = spy(new VocabularyCheck());
		doReturn(4).when(spyA).getVoc(ast);
		assertEquals(spyA.getVoc(ast),vocabulary);
		//verify once
		verify(spyA).getVoc(ast);
	}

	@Test
	public void testDifficultyCheck() throws Throwable {
		DetailAST ast = getAST("main.java");
		// real DifficultyCheck for testing getDifficulty
		DifficultyCheck a = new DifficultyCheck();
		double difficulty = a.getDifficulty(ast);
		System.out.println("Difficulty: " + difficulty);
		// spy DifficultyCheck for integration test
		DifficultyCheck spyA = spy(new DifficultyCheck());
		doReturn(1.5).when(spyA).getDifficulty(ast);
		assertTrue(spyA.getDifficulty(ast) == difficulty);
		//verify once
		verify(spyA).getDifficulty(ast);
	}

	@Test
	public void testVolumeOfOOCheck() throws Throwable {
		DetailAST ast = getAST("main.java");
		// real VolumeOfOOCheck for testing getVolume
		VolumeOfOOCheck a = new VolumeOfOOCheck();
		double volume = a.getVolume(ast);
		System.out.println("Volume: " + volume);
		// spy VolumeOfOOCheck for integration test
		VolumeOfOOCheck spyA = spy(new VolumeOfOOCheck());
		doReturn(12.0).when(spyA).getVolume(ast);
		assertTrue(spyA.getVolume(ast) == volume);
		//verify once
		verify(spyA).getVolume(ast);
	}
}
