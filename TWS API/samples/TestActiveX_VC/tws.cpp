/* Copyright (C) 2013 Interactive Brokers LLC. All rights reserved. This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable. */

// Machine generated IDispatch wrapper class(es) created by Microsoft Visual C++

// NOTE: Do not modify the contents of this file.  If this class is regenerated by
//  Microsoft Visual C++, your modifications will be overwritten.


#include "stdafx.h"
#include "tws.h"
#include "../ActiveX/tws_i.h"

/////////////////////////////////////////////////////////////////////////////
// CTws

IMPLEMENT_DYNCREATE(CTws, CWnd)

/////////////////////////////////////////////////////////////////////////////
// CTws properties

CString CTws::TwsConnectionTime()
{
	CString result;
	GetProperty(0x2a, VT_BSTR, (void*)&result);
	return result;
}

long CTws::serverVersion()
{
	long result;
	GetProperty(0x2b, VT_I4, (void*)&result);
	return result;
}

#define LAST_PROPERTY 0x36 // TrailStopPrice is 54

/////////////////////////////////////////////////////////////////////////////
// CTws operations

void CTws::cancelMktData(long id)
{
	static BYTE parms[] =
		VTS_I4;
	InvokeHelper(LAST_PROPERTY + 1, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id);
}

void CTws::cancelOrder(long id)
{
	static BYTE parms[] =
		VTS_I4;
	InvokeHelper(LAST_PROPERTY + 2, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id);
}

void CTws::placeOrder(long id, LPCTSTR action, long quantity, LPCTSTR symbol, 
					  LPCTSTR secType, LPCTSTR expiry, double strike, LPCTSTR right, LPCTSTR multiplier, 
					  LPCTSTR exchange, LPCTSTR primaryExchange, LPCTSTR currency, LPCTSTR orderType, 
					  double price, double auxPrice, LPCTSTR goodAfterTime,
					  LPCTSTR faGroup, LPCTSTR faMethod, LPCTSTR faPercentage, LPCTSTR faProfile,
					  LPCTSTR goodTillDate)
{
	static BYTE parms[] =
		VTS_I4 VTS_BSTR VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_R8 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR;
	InvokeHelper(LAST_PROPERTY + 3, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id, action, quantity, symbol, secType, expiry, 
		 strike, right, multiplier, exchange, primaryExchange,
		 currency, orderType, price, auxPrice, goodAfterTime,
		 faGroup, faMethod, faPercentage, faProfile, goodTillDate);
}

void CTws::disconnect()
{
	InvokeHelper(LAST_PROPERTY + 4, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::connect(LPCTSTR host, long port, long clientId, bool extraAuth)
{
	static BYTE parms[] =
		VTS_BSTR VTS_I4 VTS_I4 VTS_BOOL;
	InvokeHelper(LAST_PROPERTY + 5, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 host, port, clientId, extraAuth);
}

void CTws::reqMktData(long id, LPCTSTR symbol, LPCTSTR secType, LPCTSTR expiry, 
					  double strike, LPCTSTR right, LPCTSTR multiplier, LPCTSTR exchange, 
					  LPCTSTR primaryExchange, LPCTSTR currency, LPCTSTR genericTicks,
					  bool snapshot)
{
	static BYTE parms[] =
		VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BOOL;
	InvokeHelper(LAST_PROPERTY + 6, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id, symbol, secType, expiry, strike, right, multiplier, exchange, primaryExchange, currency, genericTicks, snapshot);
}

void CTws::reqOpenOrders()
{
	InvokeHelper(LAST_PROPERTY + 7, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::reqAccountUpdates(BOOL subscribe, LPCTSTR acctCode)
{
	static BYTE parms[] =
		VTS_BOOL VTS_BSTR;
	InvokeHelper(LAST_PROPERTY + 8, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 subscribe, acctCode);
}

void CTws::reqExecutions()
{
	InvokeHelper(LAST_PROPERTY + 9, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::reqIds(long numIds)
{
	static BYTE parms[] =
		VTS_I4;
	InvokeHelper(LAST_PROPERTY + 10, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 numIds);
}

void CTws::reqMktData2(long id, LPCTSTR localSymbol, LPCTSTR secType, LPCTSTR exchange,
					   LPCTSTR primaryExchange, LPCTSTR currency, LPCTSTR genericTicks,
					   bool snapshot)
{
	static BYTE parms[] =
		VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BOOL;
	InvokeHelper(LAST_PROPERTY + 11, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id, localSymbol, secType, exchange, primaryExchange, currency, genericTicks, snapshot);
}

void CTws::placeOrder2(long id, LPCTSTR action, long quantity, LPCTSTR localSymbol,
					   LPCTSTR secType, LPCTSTR exchange, LPCTSTR primaryExchange, LPCTSTR curency,
					   LPCTSTR orderType, double lmtPrice, double auxPrice,
					   LPCTSTR goodAfterTime, LPCTSTR faGroup, LPCTSTR faMethod, 
					   LPCTSTR faPercentage, LPCTSTR faProfile, LPCTSTR goodTillDate)
{
	static BYTE parms[] =
		VTS_I4 VTS_BSTR VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_R8 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR;
	InvokeHelper(LAST_PROPERTY + 12, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id, action, quantity, localSymbol, secType, exchange, primaryExchange, curency,
		 orderType, lmtPrice, auxPrice, goodAfterTime, faGroup, faMethod, 
		 faPercentage, faProfile, goodTillDate);
}

void CTws::reqContractDetails(LPCTSTR symbol, LPCTSTR secType, LPCTSTR expiry, double strike, 
							  LPCTSTR right, LPCTSTR multiplier, LPCTSTR exchange, LPCTSTR curency, long isExpired)
{
	static BYTE parms[] =
		VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_I4;
	InvokeHelper(LAST_PROPERTY + 13, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 symbol, secType, expiry, strike, right, multiplier, exchange, curency, isExpired);
}

void CTws::reqContractDetails2(LPCTSTR localSymbol, LPCTSTR secType, LPCTSTR exchange, LPCTSTR curency, long isExpired)
{
	static BYTE parms[] =
		VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_I4;
	InvokeHelper(LAST_PROPERTY + 14, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 localSymbol, secType, exchange, curency, isExpired);
}

void CTws::reqMktDepth(long id, LPCTSTR symbol, LPCTSTR secType, LPCTSTR expiry, double strike,
					   LPCTSTR right, LPCTSTR multiplier, LPCTSTR exchange, LPCTSTR curency, long numRows)
{
	static BYTE parms[] =
		VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_I4;
	InvokeHelper(LAST_PROPERTY + 15, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id, symbol, secType, expiry, strike, right, multiplier, exchange, curency, numRows);
}

void CTws::reqMktDepth2(long id, LPCTSTR localSymbol, LPCTSTR secType, LPCTSTR exchange, LPCTSTR curency, long numRows)
{
	static BYTE parms[] =
		VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_I4;
	InvokeHelper(LAST_PROPERTY + 16, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id, localSymbol, secType, exchange, curency, numRows);
}

void CTws::cancelMktDepth(long id)
{
	static BYTE parms[] =
		VTS_I4;
	InvokeHelper(LAST_PROPERTY + 17, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id);
}

void CTws::addComboLeg(long conid, LPCTSTR action, long ratio, LPCTSTR exchange,
					   long openClose, long shortSaleSlot, LPCTSTR designatedLocation, long exemptCode)
{
	static BYTE parms[] =
		VTS_I4 VTS_BSTR VTS_I4 VTS_BSTR VTS_I4 VTS_I4 VTS_BSTR VTS_I4;
	InvokeHelper(LAST_PROPERTY + 18, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 conid, action, ratio, exchange, openClose, shortSaleSlot, designatedLocation, exemptCode);
}

void CTws::clearComboLegs()
{
	InvokeHelper(LAST_PROPERTY + 19, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::cancelNewsBulletins()
{
	InvokeHelper(LAST_PROPERTY + 20, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::reqNewsBulletins(BOOL allDaysMsgs)
{
	static BYTE parms[] =
		VTS_BOOL;
	InvokeHelper(LAST_PROPERTY + 21, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 allDaysMsgs);
}

void CTws::setServerLogLevel(long logLevel)
{
	static BYTE parms[] =
		VTS_I4;
	InvokeHelper(LAST_PROPERTY + 22, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 logLevel);
}

void CTws::reqAutoOpenOrders(BOOL bAutoBind)
{
	static BYTE parms[] =
		VTS_BOOL;
	InvokeHelper(LAST_PROPERTY + 23, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 bAutoBind);
}

void CTws::reqAllOpenOrders()
{
	InvokeHelper(LAST_PROPERTY + 24, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::reqManagedAccts()
{
	InvokeHelper(LAST_PROPERTY + 25, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::requestFA(long pFaDataType)
{
	static BYTE parms[] = VTS_I4 ;
	InvokeHelper(LAST_PROPERTY + 26, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 pFaDataType);
}

void CTws::replaceFA(long pFaDataType, LPCTSTR cxml)
{
	static BYTE parms[] = VTS_I4 VTS_BSTR ;
	InvokeHelper(LAST_PROPERTY + 27, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 pFaDataType, cxml);
}

void CTws::reqHistoricalData(long id, LPCTSTR symbol, LPCTSTR secType, LPCTSTR expiry, double strike,
		LPCTSTR right, LPCTSTR multiplier, LPCTSTR exchange, LPCTSTR currency, long isExpired,
		LPCTSTR backfillEndDateTime, LPCTSTR backfillDuration, LPCTSTR barSizeSetting,
		LPCTSTR whatToShow, long useRTH, long formatDate)
{
	static BYTE parms[] =
		VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_I4 VTS_I4;
	InvokeHelper(LAST_PROPERTY + 28, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id, symbol, secType, expiry, strike, right, multiplier, exchange, currency, isExpired,
		 backfillEndDateTime, backfillDuration, barSizeSetting, whatToShow, useRTH, formatDate);
}

void CTws::exerciseOptions(long id, LPCTSTR symbol, LPCTSTR secType, LPCTSTR expiry, double strike,
		LPCTSTR right, LPCTSTR multiplier, LPCTSTR exchange, LPCTSTR currency,
		long exerciseAction, long exerciseQuantity, long exerciseOverride)
{
	static BYTE parms[] =
		VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_I4 VTS_I4 VTS_I4;
	InvokeHelper(LAST_PROPERTY + 29, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id, symbol, secType, expiry, strike, right, multiplier, exchange, currency, 
		 exerciseAction, exerciseQuantity, exerciseOverride);
}

void CTws::reqScannerParameters() {
	InvokeHelper(LAST_PROPERTY + 30, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::reqScannerSubscription(long tickerId, long numberOfRows, LPCTSTR instrument,
		LPCTSTR locationCode, LPCTSTR scanCode, double abovePrice, double belowPrice,
		long aboveVolume, double marketCapAbove, double marketCapBelow, LPCTSTR moodyRatingAbove,
		LPCTSTR moodyRatingBelow, LPCTSTR spRatingAbove, LPCTSTR spRatingBelow, LPCTSTR maturityDateAbove,
		LPCTSTR maturityDateBelow, double couponRateAbove, double couponRateBelow, long excludeConvertible,
		long averageOptionVolumeAbove, LPCTSTR scannerSettingPairs, LPCTSTR stockFilterType)
{
	static BYTE parms[] =
		VTS_I4 VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_R8 VTS_I4 VTS_R8 VTS_R8 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_R8 VTS_I4 VTS_I4 VTS_BSTR VTS_BSTR;
	InvokeHelper(LAST_PROPERTY + 31, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		tickerId, numberOfRows, instrument,	locationCode, scanCode, abovePrice, belowPrice,
		aboveVolume, marketCapAbove, marketCapBelow, moodyRatingAbove, moodyRatingBelow, 
		spRatingAbove, spRatingBelow, maturityDateAbove, maturityDateBelow, couponRateAbove,
		couponRateBelow, excludeConvertible, averageOptionVolumeAbove, scannerSettingPairs,
		stockFilterType);
}

void CTws::cancelHistoricalData(long id) {
	static BYTE parms[] = VTS_I4;
	InvokeHelper(LAST_PROPERTY + 32, DISPATCH_METHOD, VT_EMPTY, NULL, parms, id);
}

void CTws::cancelScannerSubscription(long id){
	static BYTE parms[] = VTS_I4;
	InvokeHelper(LAST_PROPERTY + 33, DISPATCH_METHOD, VT_EMPTY, NULL, parms, id);
}

void CTws::resetAllProperties() {
	InvokeHelper(LAST_PROPERTY + 34, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::reqRealTimeBars(long id, LPCTSTR symbol, LPCTSTR secType, LPCTSTR expiry, double strike,
		LPCTSTR right, LPCTSTR multiplier, LPCTSTR exchange, LPCTSTR primaryExchange, LPCTSTR currency,
		long isExpired, int barSize, LPCTSTR whatToShow, bool useRTH)
{
	static BYTE parms[] =
		VTS_I4 VTS_BSTR VTS_BSTR VTS_BSTR VTS_R8 VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_I4 VTS_I4 VTS_BSTR VTS_I4;
	InvokeHelper(LAST_PROPERTY + 35, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 id, symbol, secType, expiry, strike, right, multiplier, exchange, primaryExchange, currency, isExpired,
		 barSize, whatToShow, useRTH);
}

void CTws::cancelRealTimeBars(long id) {
	static BYTE parms[] = VTS_I4;
	InvokeHelper(LAST_PROPERTY + 36, DISPATCH_METHOD, VT_EMPTY, NULL, parms, id);
}

void CTws::reqCurrentTime() {
	InvokeHelper(LAST_PROPERTY + 37, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::reqFundamentalData(LONG reqId, IDispatch* contract, LPCTSTR reportType) {
	static BYTE parms[] = VTS_I4 VTS_DISPATCH VTS_BSTR;
	InvokeHelper(LAST_PROPERTY + 38, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		reqId, contract, reportType);
}

void CTws::cancelFundamentalData(LONG reqId) {
	static BYTE parms[] = VTS_I4;
	InvokeHelper(LAST_PROPERTY + 39, DISPATCH_METHOD, VT_EMPTY, NULL, parms, reqId);
}

void CTws::calculateImpliedVolatility(LONG reqId, IDispatch* contract, double optionPrice, double underPrice)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH VTS_R8 VTS_R8;
	InvokeHelper(/* dispidCalculateImpliedVolatility */ 94, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		reqId, contract, optionPrice, underPrice);
}

void CTws::calculateOptionPrice(LONG reqId, IDispatch* contract, double volatility, double underPrice)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH VTS_R8 VTS_R8;
	InvokeHelper(/* dispidCalculateOptionPrice */ 95, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		reqId, contract, volatility, underPrice);
}

void CTws::cancelCalculateImpliedVolatility(LONG reqId)
{
	static BYTE parms[] = VTS_I4;
	InvokeHelper(/* dispidCancelCalculateImpliedVolatility */ 96, DISPATCH_METHOD, VT_EMPTY, NULL, parms, reqId);
}

void CTws::cancelCalculateOptionPrice(LONG reqId)
{
	static BYTE parms[] = VTS_I4;
	InvokeHelper(/* dispidCancelCalculateOptionPrice */ 97, DISPATCH_METHOD, VT_EMPTY, NULL, parms, reqId);
}

void CTws::reqGlobalCancel() {
	InvokeHelper(/* dispidReqGlobalCancel */ 98, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::reqMarketDataType(LONG marketDataType) {
	static BYTE parms[] = VTS_I4;
	InvokeHelper(/* dispidReqMarketDataType */ 99, DISPATCH_METHOD, VT_EMPTY, NULL, parms, marketDataType);
}

void CTws::reqPositions() {
	InvokeHelper(/* dispidReqPositions */ 111, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::cancelPositions() {
	InvokeHelper(/* dispidCancelPositions */ 112, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}

void CTws::reqAccountSummary(LONG reqId, LPCTSTR groupName, LPCTSTR tags) {
	static BYTE parms[] = VTS_I4 VTS_BSTR VTS_BSTR;
	InvokeHelper(/* dispidReqAccountSummary */ 113, DISPATCH_METHOD, VT_EMPTY, NULL, parms, reqId, groupName, tags);
}

void CTws::cancelAccountSummary(LONG reqId) {
	static BYTE parms[] = VTS_I4;
	InvokeHelper(/* dispidCancelAccountSummary */ 114, DISPATCH_METHOD, VT_EMPTY, NULL, parms, reqId);
}

void CTws::verifyRequest(LPCTSTR apiName, LPCTSTR apiVersion) {
	static BYTE parms[] = VTS_BSTR VTS_BSTR;
	InvokeHelper(/* dispidVerifyRequest */ 115, DISPATCH_METHOD, VT_EMPTY, NULL, parms, apiName, apiVersion);
}

void CTws::verifyMessage(LPCTSTR apiData) {
	static BYTE parms[] = VTS_BSTR;
	InvokeHelper(/* dispidVerifyMessage */ 116, DISPATCH_METHOD, VT_EMPTY, NULL, parms, apiData);
}

void CTws::queryDisplayGroups(LONG reqId) {
	static BYTE parms[] = VTS_I4;
	InvokeHelper(/* dispidQueryDisplayGroups */ 117, DISPATCH_METHOD, VT_EMPTY, NULL, parms, reqId);
}

void CTws::subscribeToGroupEvents(LONG reqId, LONG groupId) {
	static BYTE parms[] = VTS_I4 VTS_I4;
	InvokeHelper(/* dispidSubscribeToGroupEvents */ 118, DISPATCH_METHOD, VT_EMPTY, NULL, parms, reqId, groupId);
}

void CTws::updateDisplayGroup(LONG reqId, LPCTSTR contractInfo) {
	static BYTE parms[] = VTS_I4 VTS_BSTR;
	InvokeHelper(/* dispidUpdateDisplayGroup */ 119, DISPATCH_METHOD, VT_EMPTY, NULL, parms, reqId, contractInfo);
}

void CTws::unsubscribeFromGroupEvents(LONG reqId) {
	static BYTE parms[] = VTS_I4;
	InvokeHelper(/* dispidUnsubscribeFromGroupEventsp */ 120, DISPATCH_METHOD, VT_EMPTY, NULL, parms, reqId);
}

void CTws::reqContractDetailsEx(LONG reqId, IDispatch* contract)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH;
	InvokeHelper(/* dispidReqContractDetailsEx */ 100, DISPATCH_METHOD, VT_EMPTY, NULL, parms, reqId, contract);
}

void CTws::reqMktDataEx(LONG tickerId, IDispatch* contract,
						LPCTSTR genericTicks, BOOL snapshot, IDispatch* mktDataOptions)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH VTS_BSTR VTS_BOOL VTS_DISPATCH;
	InvokeHelper(/* dispidReqMktDataEx */ 101, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		tickerId, contract, genericTicks, snapshot, mktDataOptions);
}

void CTws::reqMktDepthEx(LONG tickerId, IDispatch* contract, LONG numRows, IDispatch* mktDepthOptions)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH VTS_I4 VTS_DISPATCH;
	InvokeHelper(/* dispidReqMktDepthEx */ 102, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		tickerId, contract, numRows, mktDepthOptions);
}

void CTws::placeOrderEx(LONG orderId, IDispatch* contract, IDispatch* order)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH VTS_DISPATCH;
	InvokeHelper(/* dispidReqPlaceOrderEx */ 103, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		orderId, contract, order);
}

void CTws::reqExecutionsEx(LONG reqId, IDispatch* filter)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH;
	InvokeHelper(/* dispidReqExecutionsEx */ 104, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		reqId, filter);
}

void CTws::exerciseOptionsEx(LONG tickerId, IDispatch* contract,
		LONG exerciseAction, LONG exerciseQuantity, LPCTSTR account, LONG override)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH VTS_I4 VTS_I4 VTS_BSTR VTS_I4;
	InvokeHelper(/* dispidExerciseOptionsEx */ 105, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		tickerId, contract, exerciseAction, exerciseQuantity, account, override);
}

void CTws::reqHistoricalDataEx(LONG tickerId, IDispatch* contract, LPCTSTR endDateTime,
		LPCTSTR duration, LPCTSTR barSize, LPCTSTR whatToShow, BOOL useRTH, LONG formatDate, IDispatch* chartOptions)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH VTS_BSTR VTS_BSTR VTS_BSTR VTS_BSTR VTS_BOOL VTS_I4 VTS_DISPATCH;
	InvokeHelper(/* dispidReqHistoricalDataEx */ 106, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		tickerId, contract, endDateTime, duration, barSize, whatToShow, useRTH, formatDate, chartOptions);
}

void CTws::reqRealTimeBarsEx(LONG tickerId, IDispatch* contract,
		LONG barSize, LPCTSTR whatToShow, BOOL useRTH, IDispatch* realTimeBarsOptions)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH VTS_I4 VTS_BSTR VTS_BOOL VTS_DISPATCH;
	InvokeHelper(/* dispidReqRealTimeBarsEx */ 107, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		tickerId, contract, barSize, whatToShow, useRTH, realTimeBarsOptions);
}

void CTws::reqScannerSubscriptionEx(LONG tickerId, IDispatch* subscription, IDispatch* scannerSubscriptionOptions)
{
	static BYTE parms[] = VTS_I4 VTS_DISPATCH VTS_DISPATCH;
	InvokeHelper(/* dispidReqScannerSubscriptionEx */ 108, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		tickerId, subscription, scannerSubscriptionOptions);
}

void CTws::addOrderComboLeg(double price)
{
	static BYTE parms[] =
		VTS_R8;
	InvokeHelper(/* dispidAddOrderComboLeg */ 109, DISPATCH_METHOD, VT_EMPTY, NULL, parms,
		 price);
}

void CTws::clearOrderComboLegs()
{
	InvokeHelper(/* dispidClearOrderComboLegs */ 110, DISPATCH_METHOD, VT_EMPTY, NULL, NULL);
}


IDispatch* CTws::createContract(void)
{
	IDispatch* contract = NULL;
	InvokeHelper(/* dispidCreateContract */ 200, DISPATCH_METHOD, VT_DISPATCH, &contract, NULL);
	return contract;
}

IDispatch* CTws::createComboLegList(void)
{
	IDispatch* comboLegList = NULL;
	InvokeHelper(/* dispidCreateComboLegList */ 201, DISPATCH_METHOD, VT_DISPATCH, &comboLegList, NULL);
	return comboLegList;
}

IDispatch* CTws::createOrder(void)
{
	IDispatch* order = NULL;
	InvokeHelper(/* dispidCreateOrder */ 202, DISPATCH_METHOD, VT_DISPATCH, &order, NULL);
	return order;
}

IDispatch* CTws::createExecutionFilter(void)
{
	IDispatch* filter = NULL;
	InvokeHelper(/* dispidCreateExecutionFilter */ 203, DISPATCH_METHOD, VT_DISPATCH, &filter, NULL);
	return filter;
}

IDispatch* CTws::createScannerSubscription(void)
{
	IDispatch* subscr = NULL;
	InvokeHelper(/* dispidCreateScannerSubscription */ 204, DISPATCH_METHOD, VT_DISPATCH, &subscr, NULL);
	return subscr;
}

IDispatch* CTws::createUnderComp(void)
{
	IDispatch* underComp = NULL;
	InvokeHelper(/* dispidCreateUnderComp */ 205, DISPATCH_METHOD, VT_DISPATCH, &underComp, NULL);
	return underComp;
}

IDispatch* CTws::createTagValueList(void)
{
	IDispatch* tagValueList = NULL;
	InvokeHelper(/* dispidCreateTagValueList */ 206, DISPATCH_METHOD, VT_DISPATCH, &tagValueList, NULL);
	return tagValueList;
}

IDispatch* CTws::createOrderComboLegList(void)
{
	IDispatch* orderComboLegList = NULL;
	InvokeHelper(/* dispidCreateOrderComboLegList */ 207, DISPATCH_METHOD, VT_DISPATCH, &orderComboLegList, NULL);
	return orderComboLegList;
}
