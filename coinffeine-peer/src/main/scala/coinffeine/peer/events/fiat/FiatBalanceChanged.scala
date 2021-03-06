package coinffeine.peer.events.fiat

import coinffeine.common.akka.event.TopicProvider
import coinffeine.model.currency.balance.FiatBalances
import coinffeine.model.util.Cached

/** An event published when FIAT balance changes. */
case class FiatBalanceChanged(balances: Cached[FiatBalances])

object FiatBalanceChanged extends TopicProvider[FiatBalanceChanged] {
  override val Topic = "fiat.balance-changed"
}
