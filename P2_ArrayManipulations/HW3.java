//Ashley Sah
public class HW3{

   //to determine if string is in alphabetical order  
   public static boolean alphabeticalOrder(String s){
        
      //represents the index of the first letter in the string
      int firstLetter = 0;
      //represents the index of the letter in the string after the first letter
      int nextLetter = 1;
      
      //finds the first letter
      for(int i = s.length()-1; i>-1; i--){
         if( Character.isLetter( s.charAt(i) ))
            firstLetter = i;
      }
     
      //finds if the next letter in the string is less than the previous letter, then returns false
      while(nextLetter < s.length() && firstLetter <s.length() )
      {
         if( Character.isLetter( s.charAt(nextLetter) ) )
         {
            if( s.toUpperCase().charAt(nextLetter) >= s.toUpperCase().charAt(firstLetter) )
            {
              firstLetter = nextLetter;
              nextLetter++;
            }
            else
              return false;
         }
         else
            nextLetter++;
      }
      
      return true;
    }
    
    //to shift all the letters/numbers in message by a shift value
    public static String caesarCipher(String message, int shift)
    {
      if(shift<0)
         shift = shift+26;
      //represents the new, shifted message
      StringBuilder shiftMessage = new StringBuilder();
      
      //to shift the characters in message and add them to shiftMessage
      for(int i = 0; i< message.length(); i++)
      {
         //represents the current character that will be shifted
         char newChar = message.charAt(i);
         if( newChar>96 && newChar<123 )//if it's a lowercase letter
         {
            //shifts newChar incrementally (only by shift amount)
            for(int j = 0; j<shift; j++)
            {
               if(newChar == 'z')
                  newChar = 'a';
               else
                  newChar++;
            }  
         }
         if( newChar> 64 && newChar<91 )//if it's an uppercase letter
         {
            //shifts newChar incrementally (only by shift amount)
            for(int j = 0; j<shift; j++)
            {
               if(newChar == 'Z')
                  newChar = 'A';
               else
                  newChar++;
            }
         }
         if( newChar >47 && newChar<58 )//if it's a number
         {
            //shifts newChar incrementally (only by shift amount)
            for(int j = 0; j<shift; j++)
            {
               if(newChar == '9')
                  newChar = '0';
               else
                  newChar++;
            }
         }
         
         shiftMessage.append( newChar );

      }
      
      return shiftMessage.toString();
   }
   
   //to repeat all the characters in a String by a certain number
   public static String repeatChars(String message, int repeat)
   {
      //if repeat is 0, it should just return the string
      if(repeat==0)
         return message;
      //stores the message with the repeated characters
      StringBuilder reapMessage = new StringBuilder();
      
      if(repeat<0)//if the repeat param is negative, repeat backwards
      {
         //to go through each character back to front
         for(int i = message.length()-1; i > -1; i--)
         {
            //to add each character the repeat value
            for(int j = 0; j < (repeat*-1); j++)
            {
               reapMessage.append( message.charAt(i) );
            }
         }
      }
      else //if the repeat param is positive, do it normally
      {
         //to go through each character front to back
         for(int i = 0; i < message.length() ; i++)
         {
            //to add each character the repeat value
            for(int j = 0; j < repeat; j++)
            {
               reapMessage.append( message.charAt(i) );
            }
         }
      }
      
     return reapMessage.toString(); 
    
   }
   
   //to create a new array that repeats the values of array by a certain amount 
   public static double[] repeatValues(double[] array, int repeat)
   {
      //if the repeat value is 0, it should just return the array
      if(repeat == 0)
         return array;
      //represents the array with the repeated values
      double[] repeatArray = new double[repeat*array.length]; 
      
      //represents the currentIndex of repeatArray 
      int currentIndex = 0;
      
      //sets the values of repeatArray
      for(int i = 0; i<array.length; i++)
      {
         for(int j = 0; j< repeat; j++)
         {
            repeatArray[currentIndex] = array[i];
            currentIndex++;
         }
      
      }
      
      return repeatArray;
  }
  
  //repeats the words in a string by a certain amount
  public static String repeatWords(String msg, int repeat)
  {
      if(repeat == 0)//if the repeat value is 0 it should just return the message
         return msg;
      
      //represents the current index in the String msg (tells us where we are in the string)
      int indexMsg = 0;
      
      //where we will store the message with repeated words
      StringBuilder repeatMsg = new StringBuilder();
      
      //finds and adds the word repeatedly to the StringBuilder
      while( indexMsg < msg.length() )
      {
         StringBuilder word = new StringBuilder();
         
         //finds the next word in String msg
         while( indexMsg< msg.length() && Character.isLetter( msg.charAt(indexMsg) ) ){
            
            word.append( msg.charAt(indexMsg) );
            //System.out.println(word.toString() );
            indexMsg = indexMsg+1;
            //System.out.println(indexMsg);
         }
        
        //if there is a word to add...
        if(word.toString().length() != 0)
        {
        
        //adds the word to the repeatMsg 
         for(int j = 0; j<repeat; j++)
           {
             repeatMsg.append(word);
             if(j != repeat-1)
               repeatMsg.append(" ");
           }
         }
           
         //adds the non-letter character to repeatMsg
         if(indexMsg < msg.length())
            repeatMsg.append( msg.charAt(indexMsg) );
         indexMsg++;
        }
      
      return repeatMsg.toString();
   }

   //Note to grader: I tried to design isWindingPath() exactly as if a person is walking on the array
   //to check if an array of int arrays forms a winding path  
   public static boolean isWindingPath(int[][] path){
      
      //Step one: find smallest
      
      //stores the info I have on smallest value in the array
      int[] smallInfo = findSmallest(path);
      
      //represents the current column the "person" is on
      int colIndex = smallInfo[2];
      //represents the current current row the "person" is on
      int rowIndex = smallInfo[1];
      //represents the current value the person is standing on
      int centValue = smallInfo[0];
      
      //represents the surrounding values
      int[] surroundingValues = setUpValues(path, rowIndex, colIndex, centValue);
      
      //value to the left
      int left = surroundingValues[0];
      //value to the right
      int right = surroundingValues[1];
      //value on the top
      int top = surroundingValues[2]; 
      //value on the bottom
      int bottom = surroundingValues[3];
      
      //number of times a person moves, just made such that when the person moves they start on one
      int numMoves = 1;
      
      //basically I want it to run as long as there are surrounding values that are bigger than the center value
      while(left > centValue || right > centValue || top > centValue || bottom > centValue)
      {
         numMoves = numMoves +1;
         
         //represents the direction the person will move
         String direction = findDirection(surroundingValues, centValue);
         
         if( direction.equals("left") )
         {
            centValue = left;
            colIndex = colIndex -1;
         }
         if( direction.equals("right") )
         {
            centValue = right;
            colIndex = colIndex +1;
         }
         if( direction.equals("top") )
         {
            centValue = top;
            rowIndex = rowIndex -1;
         }
         if( direction.equals("bottom") )
         {
            centValue = bottom;
            rowIndex = rowIndex+1;
         }
         
         //System.out.println("Next center value: " + centValue);
         //System.out.println("Next rowIndex: " + rowIndex);
         //System.out.println("Next colIndex: " + colIndex);
         
         //finds the next set of surrounding values
         surroundingValues = setUpValues(path, rowIndex, colIndex, centValue);
         
         //reassign left, right, top, and bottom
         left = surroundingValues[0];
         right = surroundingValues[1];
         top = surroundingValues[2]; 
         bottom = surroundingValues[3];
         
         //System.out.println("next centValue: " + centValue);
  
      }
      
      //System.out.println("Final center value: " + centValue);
      //System.out.println("Final rowIndex: " + rowIndex);
      //System.out.println("Final colIndex: " + colIndex);
      //System.out.println("Number of moves: " + numMoves);
      
      //represents the number of elements in the path
      int numElements = findNumElements(path);
      
      //if number of moves equals the number of moves the person made, it's a winding path
      if( numElements == numMoves)
         return true;
      else
         return false;
   }
   
   //helper method for isWindingPath, finds the smallest value in the array
   private static int[] findSmallest(int[][] a)
   {
      //represents the smallest value in the array
      int smallest = Integer.MAX_VALUE;
      //represents the 'row' of the smallest value (which int array is it in?)
      int smallRowIndex = 0;
      //represents the 'column' of the smallest value (where is it in the int array?)
      int smallColIndex = 0;
      
      for(int i = 0; i<a.length; i++)
      {
         for(int j = 0; j<a[i].length; j++)
         {
            if(a[i][j]<smallest)
            {
               smallest = a[i][j];
               smallRowIndex = i;
               smallColIndex = j;
            }
         }
      }
      
      //need to return int[] because I want all three numbers
      int[] smallInfo = {smallest, smallRowIndex, smallColIndex};
      
      return smallInfo;
   
   }
   
   //helper method for isWindingPath(), finds the surrounding methods 
   private static int[] setUpValues(int[][]path, int rowIndex, int colIndex, int centValue){
      
      //default values if it doesn't pass conditions
      int left = centValue-1;
      int right = centValue -1;
      int top = centValue -1; 
      int bottom = centValue -1;
      
      if(colIndex > 0)//if we're not at the first element in the array
         left = path[rowIndex][colIndex-1];
         
      if(colIndex < path[rowIndex].length-1)//if we're not at the last element in the array
         right = path[rowIndex][colIndex+1];
         
      if(rowIndex > 0 && (path[rowIndex].length-path[rowIndex-1].length) <= 0)//if we're not in the first array, and the array above has a value
         top = path[rowIndex-1][colIndex];
           
      if(rowIndex < path.length-1 && path[rowIndex+1].length >= path[rowIndex].length)//if we're not in teh last array, and the array below has a value
         bottom = path[rowIndex+1][colIndex];
      
      if(rowIndex > 0 && (path[rowIndex-1].length -colIndex) > 0)//if we're still 'under' the array on top
         top = path[rowIndex-1][colIndex];
         
      if(rowIndex < path.length-1 && (path[rowIndex+1].length - colIndex) > 0)//if we're still 'above' the array below
         bottom = path[rowIndex+1][colIndex];
      
      //int[] array of the surrounding values
      int[] a = {left, right, top, bottom};
      
      return a;   
   }
   
   //helper method for isWindingPath(), determines the direction the "person" should move to
   private static String findDirection(int[] surroundingValues, int centValue)
   {
      //represents the difference between the center value and one of the possible surrounding values
      int difference = 1;
      
      //finds the direction to go in
      while(difference < Integer.MAX_VALUE ) 
      {
         if( centValue+difference == surroundingValues[0])
            return "left";
         if( centValue + difference == surroundingValues[1])
            return "right";
         if( centValue + difference == surroundingValues[2])
            return "top";
         if( centValue + difference == surroundingValues[3])
            return "bottom";
         
         difference = difference +1;
      }
      
      return "-1";//with the way the method is implemented in isWindingPath(), this method should never return -1
   }
   
   //helper method for isWindingPath, counts the number of elements in the input array
   private static int findNumElements(int[][] path)
   {
      //represents the number of elements in the array
      int numElements = 0;
      
      //counts the number of elements in the array
      for(int i = 0; i<path.length; i = i+1)
      {
         for(int j = 0; j<path[i].length; j= j+1)
         {
            numElements = numElements +1;;
         }
      }
      return numElements;
   }
   
   //extra credit assignment
   public static String cryptoQuipp(String message)
   {
      //the array of shift values for each letter, first value is shift value for 'A' and 'a', second value is shift value for 'B' and 'b', and so forth
      int[] shiftValues = new int[26];
      for(int i = 0; i<shiftValues.length-1; i++)
      {
         shiftValues[i] = -1;
      }
      
      //keeps track of if I already used a number from 0 to 25   
      boolean[] haveUsed = new boolean[26];
      
      //assigns all the values in haveUsed value to false
      for(int i= 0; i< haveUsed.length; i++)
      {
         haveUsed[i] = false;
      }
      
      //assigns random integers from 0 to 25 to the shiftValues 
      for(int i = 0; i<shiftValues.length; i++)
      {
         while(shiftValues[i] == -1)
         {
            int shift = (int)(Math.random()*26);
            if(haveUsed[shift] == false)
            {
               haveUsed[shift] = true;
               shiftValues[i] = shift;
            }
         }
      
      }
      
      //represents new message
      StringBuilder shiftMessage = new StringBuilder();
      
      for(int i = 0; i<message.length(); i++)
      {
         if(Character.isLetter( message.charAt(i) ) )
            shiftMessage.append( caesarCipher2( message.charAt(i), shiftValues[ message.toUpperCase().charAt(i)-'A' ] ) );
         else
            shiftMessage.append( message.charAt(i) );
      }
      
      return shiftMessage.toString();
   }
   
   //helper method for cryptoQuipp, just like caesarCipher but for a single char
   private static char caesarCipher2(char msg, int shift)
   {
      if( msg>96 && msg<123 )//if it's a lowercase letter
      {
        //shifts newChar incrementally (only by shift amount)
        for(int j = 0; j<shift; j++)
        {
           if(msg == 'z')
               msg = 'a';
           else
               msg = (char)(msg+1);
        }  
      }
      
      if( msg> 64 && msg<91 )//if it's an uppercase letter
      {
        //shifts char incrementally (only by shift amount)
        for(int j = 0; j<shift; j++)
        {
           if(msg == 'z')
               msg = 'a';
           else
               msg = (char)(msg+1);
        }  
      }
      
      
     if( msg >47 && msg<58 )//if it's an uppercase letter
      {
        //shifts char incrementally (only by shift amount)
        for(int j = 0; j<shift; j++)
        {
           if(msg == 'z')
               msg = 'a';
           else
               msg = (char)(msg+1);
        }  
      }
      
      
      return msg;
     
   }
   
}

/*
Test cases for isWindingPath()
  int[][] a = {{}}
  int[][] b = {{1}}
  int[][] c = {{10, 11, 14, 15}, {9, 12, 13}, {8, 1, 2, 3}, {7, 6, 5, 4}}
  int[][] d = {{1, 2}, {3, 4}}
  int[][] e = {{-4}, {-2, 9, 10}, {5, 8, 11}, {6, 7, 12, 13}}
  int[][] f = {{1, 2, 3}, {-4, 8, 10}}
  int[][] g = {{1, 9, 10, 16}, {4, 7, 11, 14}}
 */
