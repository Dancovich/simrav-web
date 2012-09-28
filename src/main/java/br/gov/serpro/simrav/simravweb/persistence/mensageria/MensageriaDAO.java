package br.gov.serpro.simrav.simravweb.persistence.mensageria;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;

import org.hornetq.api.core.HornetQException;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.ClientMessage;
import org.hornetq.api.core.client.ClientRequestor;
import org.hornetq.api.core.client.ClientSession;
import org.hornetq.api.core.client.ClientSessionFactory;
import org.hornetq.api.core.client.HornetQClient;
import org.hornetq.api.core.management.ManagementHelper;
import org.hornetq.api.core.management.ResourceNames;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.simrav.simravweb.config.ConexaoHornetQ;
import br.gov.serpro.simrav.simravweb.domain.mensageria.FilaDTO;
import br.gov.serpro.simrav.simravweb.domain.mensageria.ServidorJMSDTO;

@BusinessController
public class MensageriaDAO {
	
	@Inject
	private ConexaoHornetQ conexao;
	
	public ServidorJMSDTO pesquisarFilasJMS() {
		
		ServidorJMSDTO dto = new ServidorJMSDTO();
		
		ClientSession session = null;
		try {
			Map<String, Object> connectionParams = new HashMap<String, Object>();
			connectionParams.put(TransportConstants.HOST_PROP_NAME,	conexao.getHost());
			connectionParams.put(TransportConstants.PORT_PROP_NAME,	conexao.getPort());

			TransportConfiguration transportConfiguration = new TransportConfiguration(
					NettyConnectorFactory.class.getName(), connectionParams);
			ClientSessionFactory cf = HornetQClient.createClientSessionFactory(transportConfiguration);
			session = cf.createSession(conexao.getUser(), conexao.getPassword(), false,	
					true, true, true, HornetQClient.DEFAULT_ACK_BATCH_SIZE);
			ClientRequestor requestor = new ClientRequestor(session, "jms.queue.hornetq.management");
			session.start();
			ClientMessage message = session.createMessage(false);
			ManagementHelper.putOperationInvocation(message, ResourceNames.CORE_SERVER, "getQueueNames");
			ClientMessage reply = requestor.request(message, 60000);
			Object[] queues = (Object[]) ManagementHelper.getResult(reply);

			SortedSet<FilaDTO> filas = new TreeSet<FilaDTO>(
					new Comparator<FilaDTO>() {
						public int compare(FilaDTO dto1, FilaDTO dto2) {
							return dto1.getName().compareTo(dto2.getName());
						}
					});

			for (Object queue : queues) {
				FilaDTO fila = new FilaDTO();
				fila.setName(queue.toString());

				message = session.createMessage(false);
				ManagementHelper.putAttribute(message, ResourceNames.CORE_QUEUE	+ queue.toString(), "messageCount");
				reply = requestor.request(message, 60000);
				fila.setMessageCount(ManagementHelper.getResult(reply).toString());

				message = session.createMessage(false);
				ManagementHelper.putAttribute(message, ResourceNames.CORE_QUEUE	+ queue.toString(), "consumerCount");
				reply = requestor.request(message, 60000);
				fila.setConsumerCount(ManagementHelper.getResult(reply).toString());

				message = session.createMessage(false);
				ManagementHelper.putAttribute(message, ResourceNames.CORE_QUEUE	+ queue.toString(), "messagesAdded");
				reply = requestor.request(message, 60000);
				fila.setMessagesAdded(ManagementHelper.getResult(reply).toString());

				filas.add(fila);
			}
			
			dto.setFilas(filas.toArray(new FilaDTO[] {}));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HornetQException e) {
				}
			}

		}

		return dto;
	}

}
