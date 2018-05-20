package net.sf.eclipsecs.sample.checks;
//package net.sf.eclipsecs.

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import antlr.collections.List;
import java.util.*;
//import java.util.List;
//import java.awt.List; 

public class NumberComment extends AbstractCheck {

    private int max = 30;
    
    private int num_com = 0;
    
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
    
    // count function for counting all the comments
    public int Counts(DetailAST ast)
    {
    	// set the counts to zero and ready to hold the result and return
    	int counts = 0;
    	ArrayList<DetailAST> temp;
    	
    	// find the first token so it can start running
    	DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    	
    	// running a loop until we count all the TokenTypes we need from the object
    	while(objBlock != null)
    	{
    		temp = findChildAST(objBlock, TokenTypes.COMMENT_CONTENT);
        	
        	// counts set to holding all the child for the comments
        	counts = objBlock.getChildCount(TokenTypes.COMMENT_CONTENT);
    	}
    	
    	// return counts for ending the method
    	return counts;
    }
    
    // CHECKING
    
/*    public void checkingIF(boolean B)
    {
    	if (B)
    	{
    		System.out.println("TESTING: TRUE");
    	}
    	else
    	{
    		System.out.println("TESTING: FALSE");
    	}
    }*/
    
    // CHECKING
    
    @Override
    public void visitToken(DetailAST ast)
    {    	
    	//DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    	//DetailAST objectBlock = ast.findFirstToken(TokenTypes.COMMENT_CONTENT);
    	//single line comment or one line of a block cmnt
    	//int num_com = objBlock.getChildCount(TokenTypes.COMMENT_CONTENT);
    	int num_com = Counts(ast);
    	System.out.println("Number of comments on function count: "+ num_com );
    	//BLOCK_COMMENT_BEGIN /*
    	/*
    	 * for the program goes, get all the comment, and return the number (count)
    	 */
    	//int total = 
    	log(ast.getLineNo(), "numComment", num_com);
    	
    	//checkingIF(true);
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
