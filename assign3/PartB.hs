

{-

	TO COMPILE: 
	go to the folder where the file is saved and enter the following command
	             ghc -o PartB PartB.hs

	TO RUN:
	in the same terminal enter:
	./PartB


-}


-- this function creates takes the nth number from the list created and returns iterate
fibonacci n = fib_list!!n

-- this creates a list of fibonacci numbers using the last two numbers and iterating till required stornig it as an indexed map
-- helps with the creation of list fast and numbers can be directly accessed with the index
fib_list = map fst $ iterate (\(c,d) -> (d,d+c)) (0,1)

-- here are some test cases that can be seen on running the code
main = do
       putStrLn("0th fibonacci number is " ++ show (fibonacci 0))
       putStrLn("1th fibonacci number is " ++ show (fibonacci 1))
       putStrLn("2th fibonacci number is " ++ show (fibonacci 2))
       putStrLn("3th fibonacci number is " ++ show (fibonacci 3))
       putStrLn("12th fibonacci number is " ++ show (fibonacci 12))
       putStrLn("15th fibonacci number is " ++ show (fibonacci 15))
       putStrLn("23th fibonacci number is " ++ show (fibonacci 23))
       putStrLn("30th fibonacci number is " ++ show (fibonacci 30))
       putStrLn("150th fibonacci number is " ++ show (fibonacci 150))
       putStrLn("200th fibonacci number is " ++ show (fibonacci 200))
       putStrLn("Enter a number for nth fibonacci ")
       input <- getLine
       let x = (read input :: Int)
       print(fibonacci x)
    