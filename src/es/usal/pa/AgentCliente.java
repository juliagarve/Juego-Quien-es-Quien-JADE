package es.usal.pa;


import jade.core.Agent;

public class AgentCliente extends Agent {

	public void setup() {
		
		addBehaviour(new CyclicBehaviourRecibirPreguntasEnviarRespuestas());
	}
	
}
