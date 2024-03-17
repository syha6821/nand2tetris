// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

//CONST
@8192
D=A;
@SCRWORDS
M=D;

@SCREEN
D=A;
@SCRADDR
M=D;

(OFFLOOP)

        @i
        M=0;

    (WHTLOOP)

        @i
        D=M;

        @SCRWORDS
        D=M-D;

        @i
        M=M+1;

        @ENDWHTLOOP
        D;JLE

        @SCRADDR
        D=M
        @i
        A=D+M 
        A=A-1
        M=0;

        @WHTLOOP
        0;JMP

    (ENDWHTLOOP)

    
    @24576
    D=M;

    @ONLOOP
    D;JGT

    @OFFLOOP
    0;JMP

(ONLOOP)

        @i
        M=0;

    (BLKLOOP)

        @i
        D=M;

        @SCRWORDS
        D=M-D;

        @i
        M=M+1;

        @ENDBLKLOOP
        D;JLE

        @SCRADDR
        D=M
        @i
        A=D+M 
        A=A-1
        M=-1;

        @BLKLOOP
        0;JMP

    (ENDBLKLOOP)

    @SCREEN
    M=-1;

    @24576
    D=M;

    @OFFLOOP
    D;JEQ

    @ONLOOP
    0;JMP

(END)
    @END
    0;JMP
