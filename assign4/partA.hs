{-

	Name:Krishna Pande
	Roll: 180101038

	The following code runs successfully on Glasgow Haskell Compiler, Version 8.0.2, stage 2 booted by GHC version 8.0.2.
    This is installed on ubuntu systems using the command:
        sudo apt-get install haskell-platform

	Go to the folder containing the file and enter the following on the terminal

	To compile :
	 ghc -o partA partA.hs

	To run:
	 ./partA

-}

import Data.List.Split

-- converts the given input string of comma seperated integers to a haskell list of int
-- it splits the given string at commas to create a list of strings 
-- then reads it to map it to a list of int
giveIntList :: String -> [Int]
giveIntList listStr = map (read::String->Int) (splitOn "," listStr) 


-- this function handles the given input using various other functions
process listStr = do
    let listint = giveIntList listStr
    putStr "Entered list of intergers: "
    print listint
    putStr "******************************\n"

-- some test cases to run on the code
main = do
    process "2,3,1"
    process "8,9,7,2,3"
    process "-2,3,4,5,77,8,9,10,-1"
    process "12,33,55,66,77,0,88"
    putStrLn "Enter comma separated intergers: "
    listStr1 <- getLine
    process listStr1
    putStrLn "Enter comma separated intergers: "
    listStr2 <- getLine
    process listStr2