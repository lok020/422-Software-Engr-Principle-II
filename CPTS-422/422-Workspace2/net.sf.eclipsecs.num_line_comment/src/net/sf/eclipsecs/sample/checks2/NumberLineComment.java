package net.sf.eclipsecs.sample.checks2;
//package net.sf.eclipsecs.

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberLineComment extends AbstractCheck {

    private int max = 30;
    
    private int num_line_com = 0;
//what to look for
    @Override
    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
    }

    public void setMax(int limit) {
        max = limit;
    }
    
    public ArrayList<DetailAST> findChildAST(DetailAST parent, int type)
    {
    	ArrayList<DetailAST> children = new ArrayList<DetailAST>();
    	DetailAST first_child = parent.getFirstChild();
    	while(first_child != null)
    	{
    		if (first_child.getType() == type)
    		{
    			children.add(first_child);
    		}
    		else
    		{
    			children.addAll(findChildAST(first_child, type));
    		}
    		first_child = first_child.getNextSibling();
    	}
    	return children;
    }
    
    public int Counts(DetailAST ast)
    {
    	int counts = 0;
    	ArrayList<DetailAST> temp;
    	ArrayList<DetailAST> temp2;
    	ArrayList<DetailAST> temp3;
    	
    	// find the first token so it can start running
    	DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    	
    	// running a loop until we count all the TokenTypes we need from the object
    	while(objBlock != null)
    	{
    		temp = findChildAST(objBlock, TokenTypes.COMMENT_CONTENT);
    		temp2 = findChildAST(objBlock, TokenTypes.BLOCK_COMMENT_BEGIN);
    		temp3 = findChildAST(objBlock, TokenTypes.BLOCK_COMMENT_END);
        	
    		if (temp2 != null)
    		{
    			while(temp != temp3)
    			{
    				counts++;
    			}
    		}
    		
        	// counts set to holding all the child for the comments
        	counts = objBlock.getChildCount(TokenTypes.COMMENT_CONTENT);
    	}
    	
    	// return counts for ending the method
    	return counts;
    	
    	/*
    	int counts = 0;
    	DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    	
    	// handle all the single line comment
    	counts += objBlock.getChildCount(TokenTypes.SINGLE_LINE_COMMENT);
    	// handle all the open block line
    	counts += objBlock.getChildCount(TokenTypes.BLOCK_COMMENT_BEGIN);
    	// handle all the close block line
    	counts += objBlock.getChildCount(TokenTypes.BLOCK_COMMENT_END);
    	
    	// handle all the comment inside the block from begin to end
		//while(objBlock.getChildCount(TokenTypes.BLOCK_COMMENT_BEGIN) != objBlock.getChildCount(TokenTypes.BLOCK_COMMENT_END))
		//{
		//	counts++;
		//}
    	
    	return counts;
    	*/
    }
    
    @Override
    public void visitToken(DetailAST ast)
    {
    	//DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    	//DetailAST objectBlock = ast.findFirstToken(TokenTypes.COMMENT_CONTENT);
    	//single line comment or one line of a block cmnt
    	//int num_com = objBlock.getChildCount(TokenTypes.COMMENT_CONTENT);
    	int num_line_com = Counts(ast);
    	//BLOCK_COMMENT_BEGIN /*
    	/*
    	 * for the program goes, get all the comment, and return the number (count)
    	 */
    	//int total = 
    	log(ast.getLineNo(), "numLineComment", num_line_com);
    }

//    @Override
//    public void visitToken(DetailAST ast) {
//        // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
//        DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
//        // count the number of direct children of the OBJBLOCK
//        // that are METHOD_DEFS
//        int methodDefs = objBlock.getChildCount(TokenTypes.METHOD_DEF);
//        // report error if limit is reached
//        if (methodDefs > max) {
//            log(ast.getLineNo(), "methodlimit", max);
//        }
//    }
}
