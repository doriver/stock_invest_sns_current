function errorMessage(code) {
	if (code == "ex") {
		return "관리자에게 문의해 주세요.";
	} else if (code == "bind") {
		return "입력(요청)값 오류";
	} else if (code == "mArg") {
		return "입력값 오류";
	} else {
		return "관리자에게 문의해 주세요.";
	}
}