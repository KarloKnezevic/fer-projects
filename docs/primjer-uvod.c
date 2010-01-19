int izracunaj(float x, float y) {
	return x*y;
}

int main() {
	int i=0;
	float b=4;
	for(i=0; i <= 10; i++) b = b + izracunaj(i,2);
	while(b > i) {
		break;
	}
	return b;
}
