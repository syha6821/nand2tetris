//push constant 17
@17
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 17
@17
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//eq
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
D=D-M;
D=D+1;
M=1;
(TRUE1)
@SP
A=M;
M=M-1;
@TRUE1
D=D-1;
D;JEQ
@SP
M=M+1;
//push constant 17
@17
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 16
@16
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//eq
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
D=D-M;
D=D+1;
M=1;
(TRUE2)
@SP
A=M;
M=M-1;
@TRUE2
D=D-1;
D;JEQ
@SP
M=M+1;
//push constant 16
@16
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 17
@17
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//eq
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
D=D-M;
D=D+1;
M=1;
(TRUE3)
@SP
A=M;
M=M-1;
@TRUE3
D=D-1;
D;JEQ
@SP
M=M+1;
//push constant 892
@892
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 891
@891
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//lt
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
D=D-M;
M=1;
(TRUE4)
@SP
A=M;
M=M-1;
@TRUE4
D=-D;
D;JLT
@SP
M=M+1;
//push constant 891
@891
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 892
@892
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//lt
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
D=D-M;
M=1;
(TRUE5)
@SP
A=M;
M=M-1;
@TRUE5
D=-D;
D;JLT
@SP
M=M+1;
//push constant 891
@891
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 891
@891
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//lt
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
D=D-M;
M=1;
(TRUE6)
@SP
A=M;
M=M-1;
@TRUE6
D=-D;
D;JLT
@SP
M=M+1;
//push constant 32767
@32767
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 32766
@32766
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//gt
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
D=D-M;
M=1;
(TRUE7)
@SP
A=M;
M=M-1;
@TRUE7
D=-D;
D;JGT
@SP
M=M+1;
//push constant 32766
@32766
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 32767
@32767
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//gt
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
D=D-M;
M=1;
(TRUE8)
@SP
A=M;
M=M-1;
@TRUE8
D=-D;
D;JGT
@SP
M=M+1;
//push constant 32766
@32766
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 32766
@32766
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//gt
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
D=D-M;
M=1;
(TRUE9)
@SP
A=M;
M=M-1;
@TRUE9
D=-D;
D;JGT
@SP
M=M+1;
//push constant 57
@57
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 31
@31
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 53
@53
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//add
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
M=M+D;
@SP
M=M+1;
//push constant 112
@112
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//sub
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
M=M-D;
@SP
M=M+1;
//neg
@SP
M=M-1;
A=M;
M=-M;
@SP
M=M+1;
//and
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
M=M&D;
@SP
M=M+1;
//push constant 82
@82
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//or
@SP
M=M-1;
A=M;
D=M;
@SP
M=M-1;
A=M;
M=M|D;
@SP
M=M+1;
//not
@SP
M=M-1;
A=M;
M=!M;
@SP
M=M+1;
(END)
@END
0;JMP
