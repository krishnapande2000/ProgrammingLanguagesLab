{-

	Name:Krishna Pande
	Roll: 180101038

    The following code runs successfully on Glasgow Haskell Compiler, Version 8.0.2, stage 2 booted by GHC version 8.0.2.
    This is installed on ubuntu systems using the command:
        sudo apt-get install haskell-platform

    Go to the folder containing the file and enter the following on the terminal

	To compile :
	 ghc -o partC partC.hs

	To run:
	 ./partC

-}

import Data.List.Split

-- creating a data structure to use as a BST where each node has a value, left child, right child
-- nil is used as an blank node to indicate children of leaves etc
data Tree a = Nil | Node { value :: a, left :: Tree a, right :: Tree a}


-- this function uses recursive calls on left and right halves of the list
-- to create a BST using the middle element of the sortedList as the root each time
-- this ensures that the tree created has logn height i.e its balanced 
buildTreeBalanced :: [Int] -> Tree Int
buildTreeBalanced []   = Nil
buildTreeBalanced elts = Node (elts !! half) (buildTreeBalanced $ take half elts) (buildTreeBalanced $ drop (half+1) elts)
    where half = length elts `quot` 2

                          
-- creates a list of all elements of the tree using the root -> left child -> right child order
preOrder :: Tree Int -> [Int]
--base case
preOrder Nil = []
preOrder (Node v l r) = [v] ++ (preOrder l) ++ (preOrder r)

-- creates a list of all elements of the tree using the  left child -> root -> right child order
inOrder :: Tree Int -> [Int]
--base case
inOrder Nil = []
inOrder (Node v l r) = (inOrder l) ++ [v] ++ (inOrder r)

-- creates a list of all elements of the tree using the  left child -> right child order -> root 
postOrder :: Tree Int -> [Int]
--base case
postOrder Nil = []
postOrder (Node v l r) = (postOrder l) ++ (postOrder r) ++ [v]

--to sort a given list of int to help in creating a BST
quickSort :: Ord z => [z] -> [z]
quickSort []     = []
quickSort (f:fs) = quickSort (filter (< f) fs) ++ [f] ++ quickSort (filter (>= f) fs)

-- converts the given input string of comma seperated integers to a haskell list of int
-- it splits the given string at commas to create a list of strings 
-- then reads it to map it to a list of int
-- from partA
giveIntList :: String -> [Int]
giveIntList listStr = map (read::String->Int) (splitOn "," listStr)

-- this function handles the given input using various other functions
process listStr = do
    let l = splitOn "," listStr
    let listint = map (read::String->Int) l
    let sortedList = quickSort listint
    let root = buildTreeBalanced sortedList
    putStr "Entered list of intergers: "
    print listint
    putStr "preorder of BST of entered list of intergers: "
    print (preOrder root)
    putStr "inorder of BST of entered list of intergers: "
    print (inOrder root)    
    putStr "Post order of BST of entered list of intergers: "
    print (postOrder root)
    putStr "******************************\n"


-- some test cases to run on the code
main = do
    process "2,3,1"
    process "-3,2,4,5,20,5000"
    process "90,-8,-9,-5,-2,0,15,21"
    process "67,23,34,5,6,7,89,99,69,32"
    process "-2,-1,-4,-5"
    process "10,9,8,7,6,5,4,3,2,1"
    putStrLn "Enter comma separated intergers: "
    listStr1 <- getLine
    process listStr1
    putStrLn "Enter comma separated intergers: "
    listStr2 <- getLine
    process listStr2
    