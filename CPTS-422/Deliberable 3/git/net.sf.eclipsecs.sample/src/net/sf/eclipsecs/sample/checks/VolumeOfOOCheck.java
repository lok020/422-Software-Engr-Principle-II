package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class VolumeOfOOCheck extends AbstractCheck {

	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public void visitToken(DetailAST ast) {
		double volume = getVolume(ast);
		System.out.println("Volume: " + volume);
		log(ast.getLineNo(), "volumeOfOO", volume);
	}
	// Get volume by using Len and Voc
	public double getVolume(DetailAST cur) {
		LengthOfOOCheck l = new LengthOfOOCheck();
		VocabularyCheck v = new VocabularyCheck();
		int len = l.getLen(cur);
		int voc = v.getVoc(cur);
		
		if ((len == 0) || (voc == 0))
		{
			return 0;
		}
		return len * Math.log(voc) / Math.log(2);
	}
}