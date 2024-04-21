package es.usal.pa;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgentQuienEsQuien extends Agent {

	@Override
	public void setup(){
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setName("QuienEsQuien");
		sd.setType("Inicio conexion");
		sd.addOntologies("ontologia");
		sd.addLanguages(new SLCodec().getName());
		dfd.addServices(sd);
		
		try {
			DFService.register(this, dfd);
		} catch(FIPAException e) {
			System.err.println("Agente "+ getLocalName() + ": " +e.getMessage());
		}
		
		addBehaviour(new ParallelBehaviourPreguntas());
	}
}
