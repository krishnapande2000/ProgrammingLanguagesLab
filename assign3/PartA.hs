{-

    TO COMPILE: 
    go to the folder where the file is saved and enter the following command
            ghc -o PartA PartA.hs

    TO RUN:
    in the same terminal enter:
    ./PartA


-}



-- make_int function is to make decimal numbers into integers to use the the inbuilt haskell function for sqrt on them
make_int x = x*1000000

-- make_double function is to finally divide the number by 1000 to neutralize the multiplication by 1000000 above
make_double x = x/1000

-- sq function is to find the square root answer for the given input using the above two functions and the sqrt function
sq y = make_double(sqrt(make_int y))

-- here are some test cases that can be seen on running the code
main = do
        putStrLn("square root 13.5 = " ++ show (sq 13.5))
        putStrLn("square root 4 = " ++ show (sq 4))
        putStrLn("square root 3 = " ++ show (sq 3))
        putStrLn("square root 1000 = " ++ show (sq 1000))
        putStrLn("square root 10000 = " ++ show (sq 10000))
        putStrLn("square root 3.14 = " ++ show (sq 3.14))
        putStrLn("square root 9.0777 = " ++ show (sq 9.0777))
        putStrLn("square root 23 = " ++ show (sq 23))
        putStrLn("square root 55 = " ++ show (sq 55))
        putStrLn("square root 555555 = " ++ show (sq 555555))
        putStrLn("Enter a number for its square root ")
        input <- getLine
        let x = (read input :: Float)
        print(sq x)
        