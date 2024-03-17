//push constant 7
@7
D=A;
@SP
M=M+1;
A=M-1;
M=D;
//push constant 8
@8
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
(END)
@END
0;JMP
