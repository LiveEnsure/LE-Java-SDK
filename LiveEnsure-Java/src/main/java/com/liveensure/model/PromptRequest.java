package com.liveensure.model;

public class PromptRequest extends AgentConfigRequest {
	
	private String ques;
	private String ans;

	public PromptRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PromptRequest(String sessionToken, String ques, String ans, String required) {
		super(sessionToken, required);
		this.ques = ques;
		this.ans = ans;
	}

	public String getQues() {
		return ques;
	}

	public void setQues(String ques) {
		this.ques = ques;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	@Override
	public String toString() {
		return "Prompt [getSessionToken()=" + getSessionToken() + ", ques=" + ques + ", ans=" + ans + "]";
	}

}
