package es.usal.pa;

import java.util.Scanner;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CyclicBehaviourRecibirPreguntasEnviarRespuestas extends CyclicBehaviour 
{	
		
	@Override
	public void action() 
	{
		Utils.enviarMensaje(myAgent, "Inicio conexion", "inicio");
		
		Scanner scanner=new Scanner(System.in);

		ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.MatchConversationId(myAgent.getName()));
		System.out.println("\n\n***********QUIEN ES QUIEN***********\n\n");
		
		while(!msg.getContent().startsWith("ES")) {
			System.out.println(msg.getContent());
			String respuesta=scanner.nextLine();
			msg = msg.createReply();
			msg.setContent(respuesta);
			myAgent.send(msg);
			msg = this.myAgent.blockingReceive(MessageTemplate.MatchConversationId(myAgent.getName()));
		}
		
		System.out.println(msg.getContent());
	}
}
