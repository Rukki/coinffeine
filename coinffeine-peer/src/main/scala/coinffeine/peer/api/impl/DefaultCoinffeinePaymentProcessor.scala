package coinffeine.peer.api.impl

import akka.actor.ActorRef
import com.typesafe.scalalogging.LazyLogging

import coinffeine.model.payment.PaymentProcessor.AccountId
import coinffeine.peer.api.CoinffeinePaymentProcessor
import coinffeine.peer.config.ConfigProvider
import coinffeine.peer.payment.PaymentProcessorActor._
import coinffeine.peer.payment.PaymentProcessorProperties
import coinffeine.peer.payment.okpay.OkPayApiCredentials

private[impl] class DefaultCoinffeinePaymentProcessor(
    configProvider: ConfigProvider,
    peer: ActorRef,
    properties: PaymentProcessorProperties)
  extends CoinffeinePaymentProcessor with DefaultAwaitConfig with LazyLogging {

  private val credentialsTester = new OkPayApiCredentialsTester(configProvider.okPaySettings)

  override def accountId: Option[AccountId] = configProvider.okPaySettings().userAccount

  override val balances = properties.balances

  override def refreshBalances(): Unit = {
    peer ! RefreshBalances
  }

  override def testCredentials(credentials: OkPayApiCredentials) =
    credentialsTester.test(credentials)
}
