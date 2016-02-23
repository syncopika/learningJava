//loop practice

//to make an n x n square
for(int i = 0; i < n; i++){
   for(int j = 0; j < n; j++){
       System.out.print('*');
    }
    System.out.println();
}

//to make a triangle like this:
//   *
//  **
// ***

//there are at least two ways!

//1.
//this counter will represent the number of spaces before the asterisks
int counter = n;
for(int i = 0; i < n; i++){
   //this for loop prints the spaces
   //subtract 1 from counter because there will only be n-1 spaces in the first row
   for(int j = counter-1; j > 0; j--){
        System.out.print(' ');
        }
        
    //now decrement counter to adjust number of spaces for next row
    //and to help determine how many asterisks to put
    counter--;
    
    //this for loop prints the asterisks
    for(int k = n - counter; k > 0; k--){
        System.out.print('*');
    }
    System.out.println();
}
  
//2. 

//start at 1 because you have one asterisk in the first row!
for (int i = 1; i <= n; i++){
  //the outer for loop determines how many asterisks, right?
  //therefore, in the inner loop, we can count backwards until j == i, which will be the number of asterisks we need!
  //when j <= i, print '*'
  for(int j = n; j > 0; j--){
      if(j > i){
         System.out.print(' ');
      }else{
         System.out.print('*');
      }
      System.out.println();
  }
}

//a more interesting loop application
//i.e.
   //$$$*
   //$$*$
   //$*$$
   //*$$$
   
//you can see that the asterisk appears to move backwards by 1 every row, and it starts in the 4th column of row 1
//if variable i represents the row, and j the column, we can determine that the asterisk will be placed wherever
//i+j % 4 == 0. in row 1, 1 + 3 (column 3, using a 0-index) == 4. in row 2, 2 + 2 == 4 (in this case, column 2 is where 
//the asterisk will be).
   
for(int i = 1; i <= n; i++){
   for(int j = 0; j < n; j++){
      if((i+j)%4 == 0){
          System.out.print('*');
      }else{
         System.out.print('$');
      }
    }
    System.out.println();
  }
