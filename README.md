# nand2tetris
가상 로직게이트, cpu, 메모리, 모니터와 그 위에서 작동하는 어셈블리, 언어 구현

[nand2tetris 홈페이지](https://www.nand2tetris.org/)
nand2tetris 란 이름에서 볼 수 있듯이 nand 게이트 부터 tetris 가 돌아가는 컴퓨터를 단계적으로 만드는 코스 입니다.

이 repository 에서는 그 중 part 1 - hardware platform 을 제작하는 파트의 코드와 설명을 담고 있습니다.

projects 디렉토리에서는 각 챕터별 해결한 코드가 있습니다.

projects/01 디렉토리에서는 파트 1 에서 hdl 언어 ( nand2tetris 코스에서 직접 만든 학습용 hardware description language ) 를 통해 제가 직접 구현한 *.hdl 코드들과 미리 코스에서 만들어 놓은 *.tst, *.cmp 파일들이 있습니다. ( tst 파일을 통해 hdl 코드를 실행시키면 cmp 파일과 hdl 프로그램 실행 결과로 만들어진 out 파일을 비교해 오류가 없는 hdl 코드인지 판단합니다. )
And, And16, DMux, Dmux4Way, Dmux8Way, Mux, Mux16, Mux4Way16, Mux8Way16, Not, Not16, Or, Or16, Or8Way, Xor 로직 게이트를 구현하였습니다.

AND 게이트 영상


https://github.com/syha6821/nand2tetris/assets/83122197/2b357d10-c3af-4597-81bb-e9afeb1a7081

projects/02 에서는 이전에 만들었던 게이트들을 조합하여 Add16, FullAdder, HalfAdder, Inc16 게이트들을 만들고 이를 통해 ALU(arithmetic logic unit) 를 만들었습니다.

ALU CHIP 영상


https://github.com/syha6821/nand2tetris/assets/83122197/725d6773-1605-47a6-91a7-d17837dc7d76



projects/03 에서는 이전에 만들었던 게이트들을 조합하여 RAM8, RAM64,Register, PC(Program Counter), Bit 를 만들었습니다. 그리고 이를 확장하여 RAM512, RAM4K, RAM16K 를 만들었습니다.

PC 영상

https://github.com/syha6821/nand2tetris/assets/83122197/bf7ab203-7b40-46bc-b8fa-a7d4b3d22f17


projects/04 에서는 asm(어셈블리어)를 통해 간단한 모니터 동작과 곱셈을 구현했습니다.

간단한 모니터 동작


https://github.com/syha6821/nand2tetris/assets/83122197/374d8900-a309-4904-a7a0-aacf67cee80d



projects/05 에서는 ALU 와 메모리, 레지스터를 조합하여 CPU 를 만들어보고 그 위에서 작동하는 간단한 assembly 코드를 동작시켜 보았습니다.

assembly 로 구현한 간단한 add


https://github.com/syha6821/nand2tetris/assets/83122197/60859fed-e96f-458c-ae10-223bd31c1d44


projects/06 에서는 python 을 통해 assembly 코드를 기계어(*.hack) 로 바꾸는 assembler 를 만들어 보았습니다.

projects/06 에 python 코드와 그 하위 폴더에 assembler 의 결과인 hack 코드들이 있습니다.

projects/07 에서는 java 의 virtual machine 과 비슷한 VM을 학습하고 VM Translator 를 만들어보았습니다. 이전에 만들었던 VM(java 의 byte코드와 비슷한 역할)코드를 assembly 코드로 컴파일하는 작업입니다. (VM Translator는 main directory 에 있습니다. (nand2tetris/VM_Translator_Java) 그 결과물들은 07 directory 의 하위폴더에 있습니다.

projects/08 에서는 이전 챕터에서 만들었던 VM Translator 의 기능을 추가하는 작업을 했습니다.

projects/09 에서는 간단한 Coin 뒤집기 게임을 VM Translator 위에서 동작하는 Java와 비슷한 Jack 이라는 언어를 통해 만들었습니다.

게임 구동 영상

https://github.com/syha6821/nand2tetris/assets/83122197/b7509d54-b7f3-4b5d-bf51-9266e5f9cd75


