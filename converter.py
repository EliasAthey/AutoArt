# A converter to transform questionnaire output to processing input for AutoArt.
# Authors: Tia Smith and Elias Athey
# Date: 2/4/18

import os.path
import sys
import subprocess as proc

def main():
	if len(sys.argv) < 2:
		print("Must supply a path to the input file")
		exit()
		
	# Convert the answer file
	convert(sys.argv[1])
	
	# Run the Processing JAR to generate an image
	try:
		proc.run(["java", "-jar", "AutoArt.jar"])
	except:
		proc.run(["java.exe", "-jar", "AutoArt.jar"])


# Converts the answers.txt file
def convert(path):
	if path == None:
		
		exit()
	elif not os.path.exists(path):
		print("No " + path + " available")
		exit()
		
	converter_input = open(path,"r")
		
	results = [] # a list to hold user answers to questionnaire

	for line in converter_input:
		results.append(line.rstrip("\n"))
	converter_input.close()

	processing_input = open("params.aa", "w+")
	if results[0] == "a": # write processing parameters 1 and 2: radius, line or shape or form
		processing_input.write('25\n')
		space_depth = "3D" # draw a form
		processing_input.write('square\n')
	elif results[0] == "b":
		processing_input.write('50\n')
		space_depth = "2D" # draw a shape
		processing_input.write('circle\n')
	elif results[0] == "c":
		processing_input.write('75\n')
		space_depth = "1D" # draw a line
		if results[2] == "a":
			processing_input.write("angular\n")
		elif results[2] == "b":
			processing_input.write("curved\n")

	if results[1] == "a": # write processing parameter 3: value
		processing_input.write("0\n")
	elif results[1] == "b":
		processing_input.write("50\n")
	elif results[1] == "c":
		processing_input.write("87\n")
	elif results[1] == "d":
		processing_input.write("167\n")
	elif results[1] == "e":
		processing_input.write("209\n")
	elif results[1] == "f":
		processing_input.write("255\n")

	if results[3] == "a": # write processing parameter 4: texture
		processing_input.write("0.25\n")
	elif results[3] == "b":
		processing_input.write("0.5\n")
	elif results[3] == "c":
		processing_input.write("0.75\n")
	elif results[3] == "d":
		processing_input.write("1.0\n")

	if results[4] == "a": # write processing parameter 5: color
		processing_input.write("255,0,0\n") # red
	elif results[4] == "b":
		processing_input.write("255,215,0\n") # yellow
	if results[5] == "a":
		processing_input.write("0,0,255\n") # blue
	elif results[5] == "b":
		processing_input.write("128,0,128\n") # purple
	if results[6] == "a":
		processing_input.write("0,0,0\n") # black
	elif results[6] == "b":
		processing_input.write("255,255,255\n") # white
	if results[7] == "a":
		processing_input.write("255,165,0") # orange
	elif results[7] == "b":
		processing_input.write("0,128,0") # green

	processing_input.close()
	
if __name__ == "__main__":
	main()
	exit()
