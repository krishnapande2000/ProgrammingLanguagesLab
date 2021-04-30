
{-

	TO COMPILE: 
	go to the folder where the file is saved and enter the following command
			ghc -o PartC PartC.hs

	TO RUN:
	in the same terminal enter:
	./PartC


-}




quickSort :: Ord z => [z] -> [z]
-- this function takes input in form an array and gives array output
-- following is the base condition for the recursion defined later
quickSort []     = []

-- here we declare the answer of the function as the combinaiton of two answers and the first value in the middle of the two answers
-- we divide the elements i the array with first element of the array as a pivot and then solve the two parts recursively
-- filter function is applied on the input array to help with the partition
quickSort (f:fs) = quickSort (filter (< f) fs) ++ [f] ++ quickSort (filter (>= f) fs)

-- here are some test cases that can be seen on running the code
main = do
    putStrLn("sorting  [10,9,8,7,6,5,4,3,2,1] is " ++ show (quickSort [10,9,8,7,6,5,4,3,2,1]))
    putStrLn("sorting  [44,200,34,6,77,8,1,2900,8,5,4] is " ++ show (quickSort [44,200,34,6,77,8,1,2900,8,5,4]))
    putStrLn("sorting  [88,77,99,66,55] is " ++ show (quickSort [88,77,99,66,55]))
    putStrLn("sorting  [1.999,2,3,2.5,3.5,55.55,3,2,1] is " ++ show (quickSort [1.999,2,3,2.5,3.5,55.55,3,2,1]))
    putStrLn("sorting  [56,65,5665,56,65,56,65] is " ++ show (quickSort [56,65,5665,56,65,56,65]))
    putStrLn("sorting  [10] is " ++ show (quickSort [10]))
    putStrLn("sorting  [100,10,1,1000,10000,100000] is " ++ show (quickSort [100,10,1,1000,10000,100000]))
    putStrLn("sorting  [-7,4,5,-600,3,2,1,-1] is " ++ show (quickSort  [-7,4,5,-600,3,2,1,-1]))
    putStrLn("sorting  [6.5,-2,-1.5,-1] is " ++ show (quickSort [6.5,-2,-1.5,-1]))
    putStrLn("sorting  [1,2,3,4,5,6,7,8] is " ++ show (quickSort [1,2,3,4,5,6,7,8]))
