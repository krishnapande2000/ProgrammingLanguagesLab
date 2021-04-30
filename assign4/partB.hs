{-

    Name:Krishna Pande
    Roll: 180101038

    The following code runs successfully on Glasgow Haskell Compiler, Version 8.0.2, stage 2 booted by GHC version 8.0.2.
    This is installed on ubuntu systems using the command:
        sudo apt-get install haskell-platform

    Go to the folder containing the file and enter the following on the terminal

    To compile :
     ghc -o partB partB.hs

    To run:
     ./partB 

-}

import Data.List.Split

-- gives the lcm of list of integers using the lcm fucntion which has two inputs recursively
listLCM:: Integral a => [a] -> a
--base case
listLCM [] = 1 
--recursive step
listLCM (x:xs) = lcm x (listLCM xs) 



-- converts the given input string of comma seperated integers to a haskell list of int
-- it splits the given string at commas to create a list of strings 
-- then reads it to map it to a list of int 
--from part A
giveIntList :: String -> [Int]
giveIntList listStr = map (read::String->Int) (splitOn "," listStr) 


-- this function handles the given input using various other functions
process listStr = do
    let listint = giveIntList listStr
    let lcmlist = listLCM(listint)
    putStr "Entered list of intergers: "
    print listint
    putStr "Lcm of Entered list of intergers: "
    print lcmlist
    putStr "******************************\n"


-- some test cases to run on the code
main = do
    process "3,2,1"
    process "40,20,2,4,40"
    process "1,10,100,1000"
    process "34,51,68,102"
    process "34,35,36,37,38,39,40"
    process "45,45,45,45"
    putStrLn "Enter comma separated intergers: "
    listStr1 <- getLine
    process listStr1
    putStrLn "Enter comma separated intergers: "
    listStr2 <- getLine
    process listStr2