int globalna_varijabla;

int funkcija1(float a, float b) {
	return a*b;
}

int funkcija2() {
	int x=0;
	float b=4;
	char y='a';
	for(x=0; x < 10; x = x+1) {
		int z = x*b;
		b = z;
	}
}
