/*******************************************************************************
 *  								Assignment -5
 *	Krishna Pande, Roll-No- 180101038
 *  To run the assignment go to path of squareroot.pl file then execute the following-
 *  For executing the code 
 * 	Compile and load -> swipl -s assign_05.pl
 *  Execute different cases by runnig the following two commands now
 *  squareRootTestCases().
 * 	subListTestCases().
 *******************************************************************************/

/*For the input greater than 1 we use binary search from 1 to X*/
squareRoot(X,Result,Accuracy) :- X>1 , square_root_binary_search(1,X,X,Result,Accuracy).
/*For the input less than 1 greater than zero we use binary search from X to 1*/
squareRoot(X,Result,Accuracy) :- X>0, square_root_binary_search(X,1,X,Result,Accuracy).
/*For the input less than 0 we output an error message*/
squareRoot(X,_,_) :- X=<0 , print('Please input a positive number in the first argument').


/*Base case: If the difference is less than accuracy we have our answer in Result variable*/
square_root_binary_search(Down, Up, X, Result, Accuracy) :- Mid is (Up+Down)/2, abs(Mid*Mid-X) =< Accuracy, Result is Mid.

/*If the difference is greater than 0 we have to search before mid variable*/
square_root_binary_search(Down, Up, X, Result, Accuracy) :- Mid is (Up+Down)/2, abs(Mid*Mid-X) > Accuracy, Mid*Mid-X > 0, square_root_binary_search(Down,Mid,X,Result,Accuracy).

/*If the difference is less than 0 we have to search after the mid variable*/
square_root_binary_search(Down, Up, X, Result, Accuracy) :- Mid is (Up+Down)/2, abs(Mid*Mid-X) > Accuracy, Mid*Mid-X < 0, square_root_binary_search(Mid,Up,X,Result,Accuracy).



 /* Base case : Empty list is a subList for all lists */
 subList([],_).

 /* if same element is found we check if the rest of the list is a subList or not */
 subList([X|Ip],[X|Op]) :- subList(Ip,Op).

 /* Recursion continues to check for a subList incase elements havent matched yet */
 subList([X|Ip],[_|Op]) :- subList([X|Ip],Op).

 /* This predicate just puts the output of sublist in a result variable for eay display with testcases*/
 subListResult(X,Y,Result) :- 
( subList(X,Y)-> Result = true; Result = false).

/*7 embedded test cases for the squareRoot predicate*/
squareRootTestCases :- squareRoot(5,R1,0.0001), write('\nSquare root of 5 up to accuracy 0.0001 is-> '), print(R1),
                       squareRoot(49,R2,0.00001), write('\nSquare root of 49 up to accuracy 0.00001 is-> '), print(R2),
                       squareRoot(9,R3,0.001), write('\nSquare root of 9 up to accuracy 0.001 is-> '), print(R3),
                       squareRoot(0.0025,R4,0.000002), write('\nSquare root of 0.0025 up to accuracy 0.000002 is-> '), print(R4),
                       squareRoot(0.64,R5,0.0001), write('\nSquare root of 0.64 up to accuracy 0.0001 is-> '), print(R5),
                       squareRoot(2500,R6,0.0003), write('\nSquare root of 2500 up to accuracy 0.0003 is-> '), print(R6),
                       squareRoot(129,R7,0.5), write('\nSquare root of 129 up to accuracy 0.5 is-> '), print(R7).

 


/* 7 embedded test cases for the subList predicate*/
subListTestCases :-  subListResult([2,3],[4,2,3,6,1],R1), write('\n[2,3] is sublist of [4,2,3,6,1]->> '), print(R1),
					 subListResult([2,3],[3,2,1],R2), write('\n[2,3] is sublist of [3,2,1]->> '), print(R2),	
					 subListResult([],[],R3), write('\n[] is sublist of []->> '), print(R3),	
					 subListResult([6,7,8,9],[6,1,7,1,8,1,9],R4), write('\n[6,7,8,9] is sublist of [6,1,7,1,8,1,9] ->> '), print(R4),	
					 subListResult([5],[6,5,7],R5), write('\n[5] is sublist of [6,5,7]->> '), print(R5),	
					 subListResult([4,5,6],[4],R6), write('\n[4,5,6] is sublist of [4]->> '), print(R6),	
					 subListResult([1,2,3,4,56],[1,2,3,4,5,6],R7), write('\n[1,2,3,4,56] is sublist of [1,2,3,4,5,6]->> '), print(R7).	
                    