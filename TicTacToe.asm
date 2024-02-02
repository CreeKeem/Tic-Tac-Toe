.data
	start:	.asciiz "Tic Tac Toe Game Start!\n"
	explain:.asciiz "Enter the number to move.\n"
	choose: .asciiz "Choose X or O (1 or)"
	column: .asciiz "|"
	row: .asciiz "\n---|---|---\n"
	X: .asciiz " X "
	O: .asciiz " O "
	empty: .asciiz "   "
	newLine	: .asciiz "\n"
	prompt1: .asciiz "\nEnter a number to move:\n"
	prompt2: .asciiz "\nCurrent Board:\n"
	tie: .asciiz "\n\nIt's a Tie\n"
	invalidMsg: .asciiz "Invalid input! Please enter a number between 1 and 9.\n"
	board: .word 0,0,0,0,0,0,0,0,0 	# this is an array of 9 numbers where each index represents a box on the board (for example index 0 is the top left box and index 8 is the bottom right box
	computerWin: .asciiz "\n\nComputer Won\n"
	playerWin: .asciiz "\nYou Won!\n"

.text
.globl main
main:
	li $t7, 1	# this will be the game counter to see whose turn it is
	li $v0, 4
	la $a0, start		# Stating that the game is starting
	syscall

li $v0, 4
	la $a0, explain		#display explanations
	syscall	

	j print_board		#prints the board


check_win:
row1:				# checks if row 1 is win
la $a0, board			# loads board address
lw $t0, 0($a0)			# loads result from index 0
lw $t1, 4($a0)			# loads result from index 1
lw $t2, 8($a0)			# loads result from index 2
beq $t0, 0, row2		# checks if it is empty, i.e equals 0
bne $t0, $t2 row2		# checks if index 1 == index 2
bne $t0, $t1 row2		# checks if index 1 == index 3
j win				# if all checks out then jump to win

row2:					# checks if row 2 is win
lw $t0, 12($a0)		# loads result from index 3
lw $t1, 16($a0)		# loads result from index 4
lw $t2, 20($a0)		# loads result from index 5
beq $t0, 0, row3		# checks if it is empty, i.e equals 0
bne $t0, $t2 row3		# checks if index 3 == index 4
bne $t0, $t1 row3		# checks if index 3 == index 5
j win

row3:
lw $t0, 24($a0)		# loads result from index 6
lw $t1, 28($a0)		# loads result from index 7
lw $t2, 32($a0)		# loads result from index 8
beq $t0, 0, col1		# checks if it is empty, i.e equals 0
bne $t0, $t2 col1		# checks if index 6 == index 7
bne $t0, $t1 col1		# checks if index 6 == index 8
j win

col1:
lw $t0, 0($a0)			# loads result from index 0
lw $t1, 12($a0)		# loads result from index 3
lw $t2, 24($a0)		# loads result from index 6
beq $t0, 0, col2		# checks if it is empty, i.e equals 0
bne $t0, $t2 col2		# checks if index 0 == index 3
bne $t0, $t1 col2		# checks if index 0 == index 6
j win

col2:
lw $t0, 4($a0)			# loads result from index 1
lw $t1, 16($a0)		# loads result from index 4
lw $t2, 28($a0)		# loads result from index 7
beq $t0, 0, col3		# checks if it is empty, i.e equals 0
bne $t0, $t2 col3		# checks if index 1 == index 4
bne $t0, $t1 col3		# checks if index 1 == index 7
j win

col3:
lw $t0, 8($a0)			# loads result from index 2
lw $t1, 20($a0)		# loads result from index 5
lw $t2, 32($a0)		# loads result from index 8
beq $t0, 0, dia1		# checks if it is empty, i.e equals 0
bne $t0, $t2 dia1		# checks if index 2 == index 5
bne $t0, $t1 dia1		# checks if index 2 == index 8
j win

dia1:
lw $t0, 0($a0)			# loads result from index 0
lw $t1, 16($a0)		# loads result from index 4
lw $t2, 32($a0)		# loads result from index 8
beq $t0, 0, dia2		# checks if it is empty, i.e equals 0
bne $t0, $t2 dia2		# checks if index 0 == index 4
bne $t0, $t1 dia2		# checks if index 0 == index 8
j win

dia2:
lw $t0, 8($a0)			# loads result from index 2
lw $t1, 16($a0)		# loads result from index 4
lw $t2, 24($a0)		# loads result from index 6
beq $t0, 0, check_win_end		# checks if it is empty, i.e equals 0
bne $t0, $t2 check_win_end		# checks if index 2 == index 4
bne $t0, $t1 check_win_end		# checks if index 2 == index 6
j win

check_win_end:
j check_tie			# goes to check if there is a tie (goes in a loop between methods until there is a win or a tie)

win:
beq $t7, 1, print_comp_win	# if the counter is on 1 it shows that it was the computer win
j print_player_win		# if the counter is on 0 then it is user win

print_comp_win:
li $v0, 4
la $a0, computerWin		# print computer win
syscall
j end				# go to end

print_player_win:
li $v0, 4
la $a0, playerWin		# print user win
syscall
j end				# go to end

check_tie: 
	li $t0, 0 			# loop counter
	li $t1, 0 			# offset
	li $t2, 0 			# address plus offset
	la $a0, board 
	
tie_loop:
	beq $t0, 9, print_tie		# if the counter goes to 9 that means that all the spots on the board are not empty so go to print tie
	sll $t1, $t0, 2			# multiply counter by 4 for offset
	add $t2, $a0, $t1		# add offset to address
	lw $t3, 0($t2)			# load at index i
	beq $t3, 0, check_tie_end	# if it equals 0 then it is empty so there is no possibility of tie so jump to tie end
	addi $t0, $t0, 1		# counter++
	j tie_loop			# loop until count = 9
		
check_tie_end:
beq $t7, 0, computer_move	# use game counter to jump to next move
j player_move			#goes in a loop between methods until there is a win or a tie

print_tie:
	li $v0, 4
	la $a0, tie			# print tie
	syscall
	j end				#jump to end


player_move:				# this method gets the player's move and updates the board
	li $v0, 4
	la $a0, prompt1		# print prompt
	syscall

	li $v0, 5			# read the player's input
	syscall

	move $s0, $v0			# save the player's move in $s0
	addi $t0, $s0, -1		# check if the input is valid
	blt $t0, 0, invalid_Input	# check if 0 > input  || input > 8
	bgt $t0, 8, invalid_Input

	la $t1, board			# check if the move is already taken
	sll $t0, $t0, 2			# this is offset
	add $t1, $t1, $t0		
	lw $t2, 0($t1)			# loads number from index i
	bne $t2, 0, invalid_Input	# if the number != 0 then it is not empty so invalid input

	li $t2, 1				# update the board
	sw $t2, 0($t1)			# store input
	li $t7, 0				# changes game counter to computer turn
	j check_win			# goes to print board (goes in a loop between methods until there is a win or a tie)
	
	
computer_move:
# Generate a random number between 1 and 9
li $a0, 1
li $a1, 10
li $v0, 42
syscall

move $s0, $a0		# moves random number to $s0
	addi $t0, $s0, -1		# check if the input is valid
	blt $t0, 0, invalid_Input
	bgt $t0, 8, invalid_Input

	la $t1, board			# check if the move is already taken
	sll $t0, $t0, 2
	add $t1, $t1, $t0
	lw $t2, 0($t1)
	bne $t2, 0, invalid_Input

	li $t2, 2				# update the board
	sw $t2, 0($t1)
	li $t7, 1				# changes game counter to computer turn
	j print_board


invalid_Input:				# this method prints an error message for an invalid input
	beq $t7, 0, computer_move	# checks whose turn it is using game counter, if it is computer turn then do not need to print invalidMsg
	li $v0, 4
	la $a0, invalidMsg
	syscall
	j player_move
	

# this method prints the current board
print_board:
	li $v0, 4
	la $a0, prompt2 		# print prompt2
	syscall

la $a1, board			# loads address of the board
li $t0, 0				# this will be the offset for the array
li $t1, 0				# this will be the counter for the loop
li $t4, 3 			#used to check if a row needs to be printed

print:
add $t2, $a1, $t0		# adds the offset to the array address
lw $t3, 0($t2)			# loads the number in the index
bne $t3, 0, printX		# if the number is not 0 check if it is something else
li $v0, 4
la $a0, empty
syscall 
j continue

printX:
bne $t3, 1, printO		# prints X if the number equals 1
li $v0, 4
la $a0, X
syscall 
j continue

printO:					# prints O if the number equals 2
li $v0, 4
la $a0, O
syscall 
j continue

continue:
addi $t1, $t1, 1		# counter++
sll $t0, $t1, 2			# this multiples the offset by 4
beq $t1, 9, exitLoop 		# exit loop if counter == 9
add $t6, $t1, $zero	
div $t6, $t4			# divides the count by 3
mfhi $t5			# gets the remainder
beq $t5, $zero, printRow 	# j to print row if remainder is 0

# if remainder isn't zero then print column
li $v0, 4
la $a0, column
syscall 
j print			#jump back to print to print the next element in the array

printRow:
li $v0, 4
la $a0, row
syscall 			# print row
j print

exitLoop:
j check_win


end:
li $v0, 10 			# Set the exit syscall code
 	syscall
